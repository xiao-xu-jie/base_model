package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizEggQuotationBO;
import com.xujie.business.domain.BO.BizEggTypeBO;
import java.util.List;

public interface QuotationDomainService {
  List<BizEggTypeBO> listEggType();

  List<BizEggQuotationBO> listByEntity(BizEggQuotationBO entity);

  List<BizEggQuotationBO> listUserTodayQuotation();
}
