package com.xujie.wx.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号配置类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(prefix = "wx.mp", name = "enabled", havingValue = "true")
@Configuration
public class WxMpConfig {
    @Value("${wx.mp.appId}")
    private String appId;
    @Value("${wx.mp.appSecret}")
    private String appSecret;
    @Value("${wx.mp.token}")
    private String token;
}
