package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 展示状态枚举
 *
 * @author Xujie
 * @since 2024/10/6 14:39
 */
@AllArgsConstructor
@Getter
public enum ShowStatusEnum {
  /** 展示 */
  SHOW(1, "展示"),
  /** 隐藏 */
  HIDE(0, "隐藏");
  @JsonValue @EnumValue private final Integer code;
  private final String desc;
}
