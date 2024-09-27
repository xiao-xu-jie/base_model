package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    UNPAID(1, "未支付"),
    PAID(2, "已支付"),
    CANCEL(3, "已取消"),
    REFUND(4, "已退款");
    @EnumValue
    private final Integer code;
    private final String desc;
}
