package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VipExpireStatusEnum {
  NOT_EXPIRE(0, "未过期"),
  EXPIRED(1, "已过期");
  @EnumValue private final Integer code;
  @JsonValue private final String desc;
}
