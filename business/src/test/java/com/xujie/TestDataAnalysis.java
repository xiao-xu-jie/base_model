package com.xujie;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.businessApplication;
import com.xujie.business.common.event.QuotationPublisher;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.domain.service.DataAnalysisDomainService;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.mapper.BizEggQuotationMapper;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/10/8 13:31
 */
@Slf4j
@SpringBootTest(classes = businessApplication.class)
public class TestDataAnalysis {
  @Resource private DataAnalysisDomainService dataAnalysisDomainService;
  @Resource private BizEggQuotationMapper bizEggQuotationMapper;
  @Resource private QuotationPublisher quotationPublisher;

  @Test
  public void testGetWeekData() {
    QueryWrapper<BizEggQuotation> wrapper = new QueryWrapper<>();
    List<String> weekDateList = DateUtil.getWeekDateTimeList();
    wrapper.in("data_date", weekDateList);
    wrapper.groupBy("user_id");
    wrapper.orderBy(true, true, "data_date");
    Page<BizEggQuotation> byPage = bizEggQuotationMapper.getByPage(wrapper, new Page<>(1, 10), 1L);
    log.info("{}", JSONUtil.parse(byPage.getRecords()));
  }

  @Test
  public void testGetMonthData() {}
}
