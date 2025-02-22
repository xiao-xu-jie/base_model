package com.xujie.business.domain.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

public interface OrderDomainService {

    JSONObject createOrder(Long goodId, String user, String pass, String classJson, Integer num);

    Boolean getOrderStatus(Long orderNo);

    JSONArray queryUserOrders(String phone);
}
