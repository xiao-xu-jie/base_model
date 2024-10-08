package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizAreaUpDownDataBO;
import com.xujie.business.domain.BO.BizWeekDataBO;

public interface DataAnalysisDomainService {
  /**
   * 获取周数据
   *
   * @return 周数据
   */
  BizWeekDataBO getWeekData(Long typeId);

  /** 统计全国各地销售价以及批发价 */
  BizAreaUpDownDataBO getAreaSaleUpDownData(Long typeId);
}
