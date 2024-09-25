package com.xujie.wx.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xujie
 * @since 2024/9/15 10:20
 * Description: 微信小程序配置类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConditionalOnProperty(prefix = "wx.app", name = "enabled", havingValue = "true")
@Configuration
public class WxAppConfig {
    @Value("${wx.app.appId}")
    private String appId;
    @Value("${wx.app.appSecret}")
    private String appSecret;


    @Override
    public String toString() {
        return "WxAppConfig{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                '}';
    }
}
