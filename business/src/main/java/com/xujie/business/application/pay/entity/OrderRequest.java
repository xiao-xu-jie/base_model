package com.xujie.business.application.pay.entity;

import com.xujie.business.common.constants.HuPiJiaoPayConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    /**
     * 订单ID
     **/
    private String orderId;
    /**
     * 回调地址
     **/
    private String notifyUrl;
    /**
     * 支付成功返回地址
     **/
    private String returnUrl;
    /**
     * 订单标题
     **/
    private String title;
    /**
     * 订单价格 精确分
     **/
    private Double totalFee;
    /**
     * 订单备注
     **/
    private String remark;

    public Map<String, Object> getReqBody() {
        Map<String, Object> map = new HashMap<>();
        map.put(HuPiJiaoPayConstant.ORDER_ID, orderId);
        map.put(HuPiJiaoPayConstant.NOTIFY_URL, notifyUrl);
        map.put(HuPiJiaoPayConstant.RETURN_URL, returnUrl);
        map.put(HuPiJiaoPayConstant.TITLE, title);
        map.put(HuPiJiaoPayConstant.TOTAL_FEE, totalFee);
        map.put(HuPiJiaoPayConstant.PLUGINS, remark);
        return map;
    }
}
