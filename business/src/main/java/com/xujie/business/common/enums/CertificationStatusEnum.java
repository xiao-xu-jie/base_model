package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CertificationStatusEnum {
  UNDER_REVIEW(0, "审核中"),
  APPROVED(1, "审核通过"),
  DID_NOT_PASS(2, "审核拒绝");
  @EnumValue private final Integer code;
  @JsonValue private final String desc;
}
