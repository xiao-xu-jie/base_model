package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizCertificationBO;
import com.xujie.manager.domain.convert.BizCertificationConvert;
import com.xujie.manager.domain.service.BizCertificationDomainService;
import com.xujie.manager.infra.DO.BizCertification;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.service.BizUserCertService;
import com.xujie.manager.infra.service.BizUserService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * (BizCertification)表服务实现类
 *
 * @author xujie
 * @since 2024-10-31 11:46:36
 */
@Slf4j
@Service
public class BizCertificationDomainServiceImpl implements BizCertificationDomainService {
  @Resource private BizUserCertService bizUserCertService;
  @Resource private BizUserService bizUserService;
  @Resource private BizCertificationConvert bizCertificationConvert;

  @Resource(name = "asyncExecutor")
  private ThreadPoolTaskExecutor asyncExecutor;

  @Override
  public void add(BizCertificationBO bizCertificationBO) {
    bizUserCertService.addOne(bizCertificationConvert.convertBO2DO(bizCertificationBO));
  }

  @SneakyThrows
  @Override
  public Page<BizCertificationBO> getPageList(
      BizCertificationBO bizCertificationBO, Integer pageNum, Integer pageSize) {
    CompletableFuture<Page<BizCertificationBO>> pageListFuture =
        CompletableFuture.supplyAsync(
            () -> {
              Page<BizCertification> pageList =
                  bizUserCertService.getPageList(
                      bizCertificationConvert.convertBO2DO(bizCertificationBO), pageNum, pageSize);
              return bizCertificationConvert.convertPageDO2BO(pageList);
            },
            asyncExecutor);
    CompletableFuture<Page<BizCertificationBO>> pageCompletableFuture =
        pageListFuture.thenApply(
            bizCertificationBOPage -> {
              List<BizCertificationBO> records = bizCertificationBOPage.getRecords();
              List<Long> ids = records.stream().map(BizCertificationBO::getUserId).toList();
              List<BizUser> bizUsers = bizUserService.getUserListByUserIds(ids);
              bizCertificationBOPage
                  .getRecords()
                  .forEach(
                      item -> {
                        item.setNickName(
                            bizUsers.stream()
                                .filter(bizUser -> bizUser.getId().equals(item.getUserId()))
                                .findFirst()
                                .map(BizUser::getNickName)
                                .orElse(null));
                      });
              return bizCertificationBOPage;
            });
    return pageCompletableFuture.get(2, TimeUnit.SECONDS);
  }

  @Override
  public void delete(Long[] ids) {
    bizUserCertService.deleteBatch(ids);
  }

  @Override
  public void update(BizCertificationBO bizCertificationBO) {
    bizUserCertService.updateOne(
        bizCertificationBO.getId(), bizCertificationConvert.convertBO2DO(bizCertificationBO));
  }
}
