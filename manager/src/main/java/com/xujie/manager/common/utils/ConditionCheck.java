package com.xujie.manager.common.utils;


import com.xujie.manager.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionCheck {
    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            log.error(errorMessage);
            throw new CustomException(errorMessage);
        }
    }

    public static void trueAndThrow(boolean expression, String errorMessage) {
        if (expression) {
            throw new CustomException(errorMessage);
        }
    }
}
