package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeleteStatusEnum {
    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String desc;
}
