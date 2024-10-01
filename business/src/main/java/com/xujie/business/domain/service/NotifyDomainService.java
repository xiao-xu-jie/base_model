package com.xujie.business.domain.service;

import com.xujie.business.infra.DO.BizOrder;
import java.util.List;

public interface NotifyDomainService {
  void handlePaySuccess(Long orderNo);

  void reSubmitOrder(List<BizOrder> orderList);
}
