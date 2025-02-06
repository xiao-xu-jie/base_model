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
public class RefundRequest {
    /**
     * 订单ID
     **/
    private String orderId;

    /**
     * 退款原因
     **/
    private String reason;

    public Map<String, Object> getRefundMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(HuPiJiaoPayConstant.ORDER_ID, orderId);
        map.put(HuPiJiaoPayConstant.REFUND_REASON, reason);
        return map;
    }
}
