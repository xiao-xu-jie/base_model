package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizUserBO;
import com.xujie.manager.domain.convert.BizUserConvert;
import com.xujie.manager.domain.service.BizUserDomainService;
import com.xujie.manager.infra.DO.BizCertification;
import com.xujie.manager.infra.DO.BizEggQuotation;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.service.BizQuotationService;
import com.xujie.manager.infra.service.BizUserCertService;
import com.xujie.manager.infra.service.BizUserService;
import jakarta.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * (BizUser)表服务实现类
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@Slf4j
@Service
public class BizUserDomainServiceImpl implements BizUserDomainService {
  @Resource private BizUserService bizUserService;
  @Resource private BizUserCertService bizUserCertService;
  @Resource private BizQuotationService bizQuotationService;
  @Resource private BizUserConvert bizUserConvert;

  @Resource(name = "asyncExecutor")
  private ThreadPoolTaskExecutor asyncExecutor;

  @Override
  public void add(BizUserBO bizUserBO) {
    bizUserService.addOne(bizUserConvert.convertBO2DO(bizUserBO));
  }

  @SneakyThrows
  @Override
  public Page<BizUserBO> getPageList(BizUserBO bizUserBO, Integer pageNum, Integer pageSize) {
    CompletableFuture<Page<BizUserBO>> pageListFuture =
        CompletableFuture.supplyAsync(
            () -> {
              Page<BizUser> pageList =
                  bizUserService.getPageList(
                      bizUserConvert.convertBO2DO(bizUserBO), pageNum, pageSize);
              return bizUserConvert.convertPageDO2BO(pageList);
            },
            asyncExecutor);
    // 异步查询用户认证信息
    CompletableFuture<Page<BizUserBO>> pageCompletableFuture =
        pageListFuture.thenApply(
            bizUserBOPage -> {
              List<BizUserBO> records = bizUserBOPage.getRecords();

              if (records.isEmpty()) {
                return bizUserBOPage;
              }
              List<BizCertification> certListByUserIds =
                  bizUserCertService.getCertListByUserIds(
                      records.stream().map(BizUserBO::getId).toList());
              queryUserCert(bizUserBOPage, certListByUserIds);
              return bizUserBOPage;
            });
    // 异步查询用户的最近一次报价时间
    pageListFuture.thenApply(
        bizUserBOPage -> {
          List<BizUserBO> records = bizUserBOPage.getRecords();
          if (records.isEmpty()) {
            return null;
          }
          // 获取报价信息
          List<BizEggQuotation> quotationListByUserIds =
              bizQuotationService.getQuotationListByUserIds(
                  records.stream().map(BizUserBO::getId).toList());
          // 设置最近一次报价时间
          records.forEach(
              item -> {
                quotationListByUserIds.stream()
                    .filter(quotation -> quotation.getUserId().equals(item.getId()))
                    .max(Comparator.comparing(BizEggQuotation::getDataDate))
                    .ifPresent(
                        quotation -> {
                          item.setLastQuotationTime(quotation.getDataDate());
                        });
              });
          return records;
        });
    CompletableFuture.allOf(pageCompletableFuture, pageListFuture).join();
    return pageListFuture.get(2, TimeUnit.SECONDS);
  }

  private void queryUserCert(
      Page<BizUserBO> bizUserBOPage, List<BizCertification> certListByUserIds) {
    bizUserBOPage
        .getRecords()
        .forEach(
            item -> {
              certListByUserIds.stream()
                  .filter(cert -> cert.getUserId().equals(item.getId()))
                  .findFirst()
                  .ifPresent(cert -> setCertStatus(item, cert));
            });
  }

  private void setCertStatus(BizUserBO bizUserBO, BizCertification cert) {
    switch (cert.getCertStatus()) {
      case 0, 1, 2:
        bizUserBO.setCertificationStatus(cert.getCertStatus());
        bizUserBO.setCertName(cert.getCertType());
        break;
      default:
        break;
    }
  }

  @Override
  public void delete(Long[] ids) {
    bizUserService.deleteBatch(ids);
  }

  @Override
  public void update(BizUserBO bizUserBO) {
    bizUserService.updateOne(bizUserBO.getId(), bizUserConvert.convertBO2DO(bizUserBO));
  }
}
