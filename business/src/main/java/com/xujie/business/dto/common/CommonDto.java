package com.xujie.business.dto.common;

import lombok.Data;
import lombok.ToString;

/**
 * @author Xujie
 * @since 2025/7/1 13:20
 **/


public class CommonDto {
    @Data
    @ToString
    public static class SmsSendRequest {
        /**
         * @mock 19102849476
         * 手机号码
         */
        private String phone;
    }
}
