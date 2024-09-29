package com.xujie.business.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS 资源共享配置
 *
 * @author haoxr
 * @date 2022/10/24
 */
@Configuration
public class CorsConfig {

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    // 1.允许任何来源
    corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
    // 2.允许任何请求头
    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
    // 3.允许任何方法
    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
    // 4.允许凭证
    corsConfiguration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(source);
  }
}
