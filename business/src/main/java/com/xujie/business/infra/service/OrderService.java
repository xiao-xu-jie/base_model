package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizOrder;

import java.util.List;

public interface OrderService {
    List<BizOrder> getOrderListByEntity(BizOrder entity);

    void updateOrder(BizOrder order);
}
