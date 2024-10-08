package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizEggQuotationDay;
import java.util.List;

public interface QuotationDayService {
  List<BizEggQuotationDay> getQuotationDayListByTime(String startTime, String endTime, Long typeId);
}
