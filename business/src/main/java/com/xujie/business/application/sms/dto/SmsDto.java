package com.xujie.business.application.sms.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Xujie
 * @since 2025/6/30 23:13
 **/

public class SmsDto {

    @Data
    @ToString
    public static class SmsSendRequest {
        private String phone;
        private String content;
    }

    @Data
    @ToString
    public static class SmsSendHttpRequest {
        private String url;
        private String channel;
        private String username;
        private String key;
        private String phone;
        private String content;
    }

    @Data
    @ToString
    public static class SmsSendResponse {
        private int code;
        private String message;
        private List<String> success_data;

        public Boolean isSuccess() {
            return code == 0;
        }
    }
}
