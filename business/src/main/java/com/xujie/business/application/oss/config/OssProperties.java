package com.xujie.business.application.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: AKang
 * @Description: OSS属性封装类
 * @CreateTime: 2025-05-10
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class OssProperties {
    /**
     * accessKeyId
     **/
    private String accessKeyId;
    /**
     * accessKeySecret
     **/
    private String accessKeySecret;
    /**
     * STS域名
     **/
    private String endpoint;
    /**
     * RAM角色账号
     **/
    private String ramRoleArn;
}
