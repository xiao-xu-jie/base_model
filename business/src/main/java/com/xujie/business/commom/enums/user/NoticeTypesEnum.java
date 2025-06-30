package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeTypesEnum {
    SYSTEM_NOTICE(1L, "系统通知");
    private final Long code;
    private final String desc;
}
