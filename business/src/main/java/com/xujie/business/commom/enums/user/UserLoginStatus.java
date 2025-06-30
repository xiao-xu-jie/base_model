package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserLoginStatus {
    SUCCESS(1, "登录成功"),
    FAIL(0, "登录失败");
    private final Integer code;
    private final String desc;
}
