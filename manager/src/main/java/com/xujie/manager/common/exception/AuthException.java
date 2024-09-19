package com.xujie.manager.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthException extends BaseException {

    public AuthException(String message, Integer code) {
        super(code, message);
    }
}
