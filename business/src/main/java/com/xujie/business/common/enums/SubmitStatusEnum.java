package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SubmitStatusEnum {
  NONE(-1, "未提交"),
  SUBMIT_SUCCESS(1, "提交成功"),
  SUBMIT_RETRY(2, "提交重试"),
  SUBMIT_FAIL(0, "提交失败");

  @EnumValue private final Integer code;
  @JsonValue private final String desc;
}
