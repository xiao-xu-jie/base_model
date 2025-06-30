package com.xujie.business.application.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Xujie
 * @since 2025/6/30 23:18
 **/

@Data
@ConfigurationProperties(prefix = "sms")
public class SmsConfiguration {
    private String url;
    private String channel;
    private String username;
    private String key;
}
