package com.xujie.business.domain.service;

public interface OrderDomainService {
  Long createOrder(Long goodId, String user, String pass, String classJson, Integer num);

  Boolean getOrderStatus(Long orderNo);
}
