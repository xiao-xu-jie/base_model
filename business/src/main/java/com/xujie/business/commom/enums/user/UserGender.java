package com.xujie.business.commom.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGender {
    MAN(0, "男"),
    WOMAN(1, "女");
    private Integer code;
    private String desc;
}
