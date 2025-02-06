package com.xujie.business.application.pay;

import cn.hutool.json.JSONObject;
import com.xujie.business.application.pay.entity.OrderRequest;
import com.xujie.business.application.pay.entity.RefundRequest;

public interface PayService {
    JSONObject createOrder(OrderRequest orderRequest);

    JSONObject refundOrder(RefundRequest refundRequest);

}
