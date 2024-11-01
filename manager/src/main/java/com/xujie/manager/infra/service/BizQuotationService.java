package com.xujie.manager.infra.service;

import com.xujie.manager.common.base.service.BaseService;
import com.xujie.manager.infra.DO.BizEggQuotation;
import java.util.List;

public interface BizQuotationService extends BaseService<BizEggQuotation> {
  List<BizEggQuotation> getQuotationListByUserIds(List<Long> userIds);
}
