package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NoticeNotifyStatus {
    FAILED(0, "失败"),
    SUCCESS(1, "成功"),
    GIVE_UP(2, "放弃通知");

    private final Integer code;
    private final String desc;
}
