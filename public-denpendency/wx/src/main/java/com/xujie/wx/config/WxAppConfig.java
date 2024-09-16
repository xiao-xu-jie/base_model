package com.xujie.wx.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xujie
 * @since 2024/9/15 10:20
 * Description: 微信小程序配置类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "wx.app")
public class WxAppConfig {
    private String appId;
    private String appSecret;


    @Override
    public String toString() {
        return "WxAppConfig{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                '}';
    }
}
