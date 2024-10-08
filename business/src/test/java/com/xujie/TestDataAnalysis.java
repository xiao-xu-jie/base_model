package com.xujie;

import com.xujie.business.businessApplication;
import com.xujie.business.domain.BO.BizWeekDataBO;
import com.xujie.business.domain.service.DataAnalysisDomainService;
import jakarta.annotation.Resource;
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

  @Test
  public void testGetWeekData() {
    BizWeekDataBO weekData = dataAnalysisDomainService.getWeekData(1L);
    log.debug("weekData: {}", weekData);
  }
}
