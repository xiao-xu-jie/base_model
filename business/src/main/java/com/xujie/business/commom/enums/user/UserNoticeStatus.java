package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserNoticeStatus {
    UN_READ(0, "未读"),
    READ(1, "已读");

    private final Integer code;
    private final String desc;

}
