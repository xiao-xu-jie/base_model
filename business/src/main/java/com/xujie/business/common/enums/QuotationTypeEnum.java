package com.xujie.business.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuotationTypeEnum {
  ACQUISITION(1, "收购价"),
  SALES(2, "销售价");
  @EnumValue private final Integer code;
  @JsonValue private final String desc;

  public static QuotationTypeEnum getEnumByCode(Integer quotationType) {
    for (QuotationTypeEnum value : QuotationTypeEnum.values()) {
      if (value.getCode().equals(quotationType)) {
        return value;
      }
    }
    throw new IllegalArgumentException("未知的报价类型");
  }
}
