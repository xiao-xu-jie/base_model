package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatusEnum {
  ENABLE(1, "启用"),
  DISABLE(0, "禁用");
  @EnumValue private final Integer code;
  @JsonValue private final String desc;
}
