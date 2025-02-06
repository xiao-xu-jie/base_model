package com.xujie.business.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@EnableConfigurationProperties(value = HuPiJiaoPayConfig.class)
@ConfigurationProperties(prefix = "pay.hupijiao")
//@ConditionalOnProperty(prefix = "pay.hupijiao", name = {"Appid", "AppSecret"})
public class HuPiJiaoPayConfig {
    private String url;
    private String refundUrl;
    private String Appid;
    private String AppSecret;
}
