package com.xujie.manager.infra.service;

import com.xujie.manager.infra.DO.BizEggQuotation;
import java.util.List;

public interface BizQuotationService {
  List<BizEggQuotation> getQuotationListByUserIds(List<Long> userIds);
}
