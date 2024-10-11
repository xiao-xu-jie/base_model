package com.xujie.business.domain.schedule;

import com.xujie.business.common.enums.QuotationTypeEnum;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggQuotationDay;
import com.xujie.business.infra.service.QuotationDayService;
import com.xujie.business.infra.service.QuotationService;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 今日分析任务
 *
 * @author Xujie
 * @since 2024/10/10 11:38
 */
@Slf4j
@Component
public class AnalysisTodayTask {

  @Resource private QuotationService quotationService;
  @Resource private QuotationDayService quotationDayService;

  @Scheduled(cron = "0 0/1 * * * ? ")
  public void analysisToday() {
    log.info("[今日数据分析任务]：开始执行");
    log.info("[今日数据分析任务]：删除今日数据");
    quotationDayService.deleteByTime(DateUtil.getTodayString());

    List<BizEggQuotation> bizEggQuotations =
        quotationService.listByEntity(
            BizEggQuotation.builder().dataDate(DateUtil.getTodayString()).build());
    Map<Integer, List<BizEggQuotation>> collect =
        bizEggQuotations.stream().collect(Collectors.groupingBy(BizEggQuotation::getQuotationType));
    List<BizEggQuotationDay> bizEggQuotationDays = new ArrayList<>();
    collect.forEach(
        (k, v) -> {
          log.info("[今日数据分析任务]：报价类型：{}，数量：{}", k, v.size());
          Map<Long, List<BizEggQuotation>> typeList =
              v.stream().collect(Collectors.groupingBy(BizEggQuotation::getEggTypeId));
          typeList.forEach(
              (k1, v1) -> {
                log.info("[今日数据分析任务]：蛋种类：{}，数量：{}", k1, v1.size());
                BizEggQuotationDay build =
                    BizEggQuotationDay.builder()
                        .dataTime(DateUtil.getTodayString())
                        .quotationType(QuotationTypeEnum.getEnumByCode(k))
                        .typeId(k1)
                        .peopleNumber(v1.size())
                        .build();

                if (k == 1) {
                  Double maxNum =
                      v1.stream()
                          .mapToDouble(BizEggQuotation::getQuotationMaxPrice)
                          .max()
                          .orElse(0.0);
                  build.setMaxNum(maxNum);
                  Double avg =
                      v1.stream()
                              .reduce(0.0, (sum, p) -> sum + p.getQuotationAvgPrice(), Double::sum)
                          / v1.size();
                  build.setAvgNum(avg);
                }
                if (k == 2) {
                  Double minNum =
                      v1.stream()
                          .mapToDouble(BizEggQuotation::getQuotationMinPrice)
                          .min()
                          .orElse(0.0);
                  build.setMinNum(minNum);
                  Double avg =
                      v1.stream()
                          .mapToDouble(BizEggQuotation::getQuotationAvgPrice)
                          .average()
                          .orElse(0.0);
                  build.setAvgSaleNum(avg);
                }
                bizEggQuotationDays.add(build);
              });
        });
    int i = quotationDayService.addOrUpdateQuotationDayList(bizEggQuotationDays);
    log.info("[今日数据分析任务]：新增或更新数据：{}", i);
    log.info("[今日数据分析任务]：执行结束");
  }
}
