package com.xujie.business.domain.service.impl;

import com.xujie.business.common.enums.QuotationTypeEnum;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.domain.BO.BizAreaUpDownDataBO;
import com.xujie.business.domain.BO.BizWeekDataBO;
import com.xujie.business.domain.service.DataAnalysisDomainService;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggQuotationDay;
import com.xujie.business.infra.service.QuotationDayService;
import com.xujie.business.infra.service.QuotationService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * 数据统计领域服务实现
 *
 * @author Xujie
 * @since 2024/10/8 13:09
 */
@Slf4j
@Service
public class DataAnalysisDomainServiceImpl implements DataAnalysisDomainService {
  @Resource private QuotationDayService quotationDayService;
  @Resource private QuotationService quotationService;

  /**
   * 获取周数据
   *
   * @param typeId
   * @return 周数据
   */
  @Override
  public BizWeekDataBO getWeekData(Long typeId) {
    String todayString = DateUtil.getTodayString();
    String weekStart = DateUtil.getDateStringBefore(6);
    List<BizEggQuotationDay> quotationDayListByTime =
        quotationDayService.getQuotationDayListByTime(weekStart, todayString, typeId);
    Map<QuotationTypeEnum, List<BizEggQuotationDay>> collect =
        quotationDayListByTime.stream()
            .collect(Collectors.groupingBy(BizEggQuotationDay::getQuotationType));

    return handle(collect);
  }

  private BizWeekDataBO handle(Map<QuotationTypeEnum, List<BizEggQuotationDay>> collect) {
    QuotationTypeEnum[] values = QuotationTypeEnum.values();
    BizWeekDataBO bizWeekDataBO = new BizWeekDataBO();
    List<BizEggQuotationDay> salesList = bizWeekDataBO.getSalesList();
    List<BizEggQuotationDay> buysList = bizWeekDataBO.getBuyList();
    int todayOfMonth = DateTime.parse(DateUtil.getTodayString()).getDayOfMonth();
    for (int i = 0; i < values.length; i++) {
      QuotationTypeEnum typeEnum = values[i];
      List<BizEggQuotationDay> bizEggQuotationDays = collect.get(typeEnum);
      if (bizEggQuotationDays != null) {
        if (QuotationTypeEnum.SALES.equals(typeEnum)) {
          for (int j = 0; j < bizEggQuotationDays.size(); j++) {
            int dayOfMonth =
                DateTime.parse(bizEggQuotationDays.get(j).getDataTime()).getDayOfMonth();
            int index = 6 - todayOfMonth + dayOfMonth;
            salesList.set(index, bizEggQuotationDays.get(j));
          }

        } else {
          for (int j = 0; j < bizEggQuotationDays.size(); j++) {
            int dayOfMonth =
                DateTime.parse(bizEggQuotationDays.get(j).getDataTime()).getDayOfMonth();
            int index = 6 - todayOfMonth + dayOfMonth;
            buysList.set(index, bizEggQuotationDays.get(j));
          }
        }
      }
    }
    return bizWeekDataBO;
  }

  @Override
  public BizAreaUpDownDataBO getAreaSaleUpDownData(Long typeId) {
    BizAreaUpDownDataBO bizAreaUpDownDataBO = new BizAreaUpDownDataBO();
    String today = DateUtil.getTodayString();
    List<BizEggQuotation> bizEggQuotations =
        quotationService.listByEntity(
            BizEggQuotation.builder().dataDate(today).eggTypeId(typeId).build());
    List<BizEggQuotation> saleList =
        bizEggQuotations.stream()
            .filter(item -> item.getQuotationType().equals(QuotationTypeEnum.SALES.getCode()))
            .toList();
    List<BizEggQuotation> buyList =
        bizEggQuotations.stream()
            .filter(item -> item.getQuotationType().equals(QuotationTypeEnum.ACQUISITION.getCode()))
            .toList();
    // 统计status
    handleSale(saleList, bizAreaUpDownDataBO);
    handleBuy(buyList, bizAreaUpDownDataBO);
    return bizAreaUpDownDataBO;
  }

  private void handleBuy(List<BizEggQuotation> buyList, BizAreaUpDownDataBO bizAreaUpDownDataBO) {
    AtomicInteger buyUp = new AtomicInteger();
    AtomicInteger buyDown = new AtomicInteger();
    AtomicInteger buyNormal = new AtomicInteger();
    buyList.stream()
        .map(BizEggQuotation::getQuotationFloatStatus)
        .forEach(
            item -> {
              // 统计
              switch (item) {
                case -1 -> buyUp.getAndIncrement();
                case 0 -> buyNormal.getAndIncrement();
                case 1 -> buyDown.getAndIncrement();
                default -> {
                  log.error("未知的status:{}", item);
                }
              }
            });
    int total = buyUp.get() + buyNormal.get() + buyDown.get();
    if (total != 0) {
      bizAreaUpDownDataBO.getBuyData().setUpPercent((double) buyUp.get() / total * 100);
      bizAreaUpDownDataBO.getBuyData().setFlatPercent((double) buyNormal.get() / total * 100);
      bizAreaUpDownDataBO.getBuyData().setDownPercent((double) buyDown.get() / total * 100);
    }
  }

  private static void handleSale(
      List<BizEggQuotation> saleList, BizAreaUpDownDataBO bizAreaUpDownDataBO) {
    AtomicInteger saleUp = new AtomicInteger();
    AtomicInteger saleDown = new AtomicInteger();
    AtomicInteger saleNormal = new AtomicInteger();
    saleList.stream()
        .map(BizEggQuotation::getQuotationFloatStatus)
        .forEach(
            item -> {
              // 统计
              switch (item) {
                case -1 -> saleUp.getAndIncrement();
                case 0 -> saleNormal.getAndIncrement();
                case 1 -> saleDown.getAndIncrement();
                default -> {
                  log.error("未知的status:{}", item);
                }
              }
            });
    int total = saleUp.get() + saleNormal.get() + saleDown.get();
    if (total != 0) {
      bizAreaUpDownDataBO.getSaleData().setUpPercent((double) saleUp.get() / total * 100);
      bizAreaUpDownDataBO.getSaleData().setFlatPercent((double) saleNormal.get() / total * 100);
      bizAreaUpDownDataBO.getSaleData().setDownPercent((double) saleDown.get() / total * 100);
    }
  }
}
