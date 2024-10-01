package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GoodStatusEnum {
  UP(1, "上架"),
  DOWN(2, "下架");

  @EnumValue private final Integer code;
  @JsonValue private final String desc;
}
