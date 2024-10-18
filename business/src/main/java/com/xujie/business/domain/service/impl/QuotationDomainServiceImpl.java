package com.xujie.business.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.common.event.QuotationPublisher;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.convert.QuotationConvert;
import com.xujie.business.domain.BO.BizEggQuotationBO;
import com.xujie.business.domain.BO.BizEggTypeBO;
import com.xujie.business.domain.service.QuotationDomainService;
import com.xujie.business.domain.service.UserDomainService;
import com.xujie.business.domain.service.VipDomainService;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggType;
import com.xujie.business.infra.service.QuotationService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 报价领域服务实现
 *
 * @author Xujie
 * @since 2024/10/6 13:02
 */
@Slf4j
@Service
public class QuotationDomainServiceImpl implements QuotationDomainService {
  @Resource private QuotationService quotationService;
  @Resource private UserDomainService userDomainService;
  @Resource private VipDomainService vipDomainService;
  @Resource private QuotationConvert quotationConvert;
  @Resource private QuotationPublisher quotationPublisher;

  @Resource(name = "asyncExecutor")
  private ThreadPoolTaskExecutor asyncExecutor;

  @Override
  public List<BizEggTypeBO> listEggType() {
    List<BizEggType> bizEggTypes = quotationService.listEggType();
    return quotationConvert.convertEggTypeDOList2BOList(bizEggTypes);
  }

  @Override
  public List<BizEggQuotationBO> listByEntity(BizEggQuotationBO entity) {
    List<BizEggQuotation> bizEggQuotations =
        quotationService.listByEntity(quotationConvert.convertEggQuotationBO2DO(entity));
    return quotationConvert.convertEggQuotationDOList2BOList(bizEggQuotations);
  }

  @Override
  public List<BizEggQuotationBO> listUserTodayQuotation() {
    Long userId = StpUtil.getLoginIdAsLong();
    ConditionCheck.nullAndThrow(userId, new CustomException("用户未登录"));

    log.info("[报价][获取用户今日报价]时间：{}", DateUtil.getTodayString());
    BizEggQuotation build =
        BizEggQuotation.builder().userId(userId).dataDate(DateUtil.getTodayString()).build();
    List<BizEggQuotation> bizEggQuotations = quotationService.listByEntity(build);
    return quotationConvert.convertEggQuotationDOList2BOList(bizEggQuotations);
  }

  @Override
  public Page<BizEggQuotationBO> selectPage(
      BizEggQuotationBO entity, Integer pageNum, Integer pageSize) {
    Page<BizEggQuotation> bizEggQuotationPage =
        quotationService.selectPage(
            quotationConvert.convertEggQuotationBO2DO(entity), pageNum, pageSize);
    return quotationConvert.convertEggQuotationPageDO2BO(bizEggQuotationPage);
  }

  @Override
  public void submitTodayQuotation(BizEggQuotationBO entity) {
    // 设置用户ID
    entity.setUserId(StpUtil.getLoginIdAsLong());
    entity.setQuotationFloatStatus(0);
    entity.setDataDate(DateUtil.getTodayString());
    // 判断是否已经存在
    BizEggQuotation search =
        BizEggQuotation.builder()
            .userId(entity.getUserId())
            .dataDate(entity.getDataDate())
            .quotationType(entity.getQuotationType())
            .eggTypeId(entity.getEggTypeId())
            .quotationLocation(entity.getQuotationLocation())
            .build();
    BizEggQuotation searchRes = quotationService.getByEntity(search);
    if (searchRes != null) {
      log.error("[报价][提交报价]用户{}今日已经提交过报价", entity.getUserId());
      throw new CustomException("今日已经提交过该地区种类的报价");
    }
    // 计算同地区同日期下价格波动情况
    CompletableFuture<Void> voidCompletableFuture =
        CompletableFuture.runAsync(
                () -> {
                  BizEggQuotation build =
                      BizEggQuotation.builder()
                          .quotationType(entity.getQuotationType())
                          .userId(entity.getUserId())
                          .dataDate(DateUtil.getDateStringBefore(1))
                          .quotationLocation(entity.getQuotationLocation())
                          .build();
                  BizEggQuotation byEntity = quotationService.getByEntity(build);
                  if (byEntity != null) {

                    if (entity.getQuotationAvgPrice() > byEntity.getQuotationAvgPrice()) {
                      log.info(
                          "[报价][价格波动]{}价格上涨，上涨幅度：{}",
                          entity.getUserId(),
                          entity.getQuotationAvgPrice() - byEntity.getQuotationAvgPrice());
                      entity.setQuotationFloatStatus(1);
                    } else {
                      log.info(
                          "[报价][价格波动]{}价格下跌，下跌幅度：{}",
                          entity.getUserId(),
                          byEntity.getQuotationAvgPrice() - entity.getQuotationAvgPrice());
                      entity.setQuotationFloatStatus(-1);
                    }
                  }
                },
                asyncExecutor)
            .handle(
                (aVoid, throwable) -> {
                  if (throwable != null) {
                    log.error("[报价][价格波动]价格波动计算异常", throwable);
                  }
                  return null;
                });
    voidCompletableFuture.join();
    try {
      quotationPublisher.publishEvent(StpUtil.getLoginIdAsLong(), entity);
    } catch (Exception e) {
      log.error("[报价][提交报价]发布事件异常", e);
    }
    try {
      quotationService.add(quotationConvert.convertEggQuotationBO2DO(entity));

    } catch (Exception e) {
      log.error("[报价][提交报价]异常", e);
      throw new CustomException("提交报价失败");
    }
  }

  @Override
  public List<BizEggQuotationBO> getUserQuotationByDate(String date) {
    Long userId = StpUtil.getLoginIdAsLong();
    ConditionCheck.nullAndThrow(userId, new CustomException("用户未登录"));
    BizEggQuotation build = BizEggQuotation.builder().userId(userId).dataDate(date).build();
    List<BizEggQuotation> bizEggQuotations = quotationService.listByEntity(build);
    return quotationConvert.convertEggQuotationDOList2BOList(bizEggQuotations);
  }

  @Override
  public void updateTodayQuotation(BizEggQuotationBO entity) {
    // 判断是否是已经提交的报价
    BizEggQuotation search =
        BizEggQuotation.builder()
            .userId(StpUtil.getLoginIdAsLong())
            .quotationType(entity.getQuotationType())
            .eggTypeId(entity.getEggTypeId())
            .quotationLocation(entity.getQuotationLocation())
            .dataDate(DateUtil.getTodayString())
            .build();
    BizEggQuotation searchRes = quotationService.getByEntity(search);

    if (searchRes != null && !searchRes.getId().equals(entity.getId())) {
      log.error("[报价][更新报价]用户{}今日已经提交过报价", entity.getUserId());
      throw new CustomException("今日已经提交过该地区种类的其他报价");
    }

    quotationService.updateQuotation(quotationConvert.convertEggQuotationBO2DO(entity));
  }
}
