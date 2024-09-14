package com.xujie.business.common.exception;

import com.xujie.business.common.enums.BaseResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Xujie
 * @Date: 2024/7/15 21:58
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException(BaseResultEnum baseResultEnum) {
        this.code = baseResultEnum.getCode();
        this.message = baseResultEnum.getMessage();
    }

    public CustomException(String s) {
        this.message = s;
        this.code = BaseResultEnum.FAIL.getCode();
    }
}
