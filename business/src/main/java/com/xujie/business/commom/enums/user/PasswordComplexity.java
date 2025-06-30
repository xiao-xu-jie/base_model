package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PasswordComplexity {
    High(1, "高"),
    Middle(2, "中"),
    Low(3, "低");
    private Integer code;
    private String desc;
}
