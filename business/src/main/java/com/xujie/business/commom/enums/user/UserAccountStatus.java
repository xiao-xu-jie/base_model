package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAccountStatus {
    NORMAL(0, "正常"),
    DISABLED(1, "禁用");
    private Integer code;
    private String desc;
}
