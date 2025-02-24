package com.xujie.business.domain.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.xujie.business.infra.DO.BizOrder;

import java.util.List;

public interface OrderDomainService {

    JSONObject createOrder(Long goodId, String user, String pass, String classJson, Integer num);

    Boolean getOrderStatus(Long orderNo);

    JSONArray queryUserOrders(String phone);

    public List<BizOrder> getAllInProgressOrders();
}
