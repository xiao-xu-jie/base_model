package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Xujie
 * @since 2025/6/1 10:41
 **/

@Getter
@AllArgsConstructor
public enum NoticeTypeEnum {
    IN_SITE(0, "站内"),
    EMAIL(1, "邮件"),
    TEXT_MESSAGE(2, "短信");
    private final Integer code;
    private final String desc;
}
