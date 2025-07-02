package com.xujie.business.commom.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "sa-token.filter")
public class SaTokenFilterProperties {

    /**
     * 需要拦截的路由
     */
    private List<String> include = Arrays.asList("/**");

    /**
     * 需要放行的路由
     */
    private List<String> exclude = new ArrayList<>();

    /**
     * 登录认证放行的路由
     */
    private List<String> authExclude = Arrays.asList("/user/doLogin");
}
