package com.xujie.business.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 站点配置
 *
 * @author Xujie
 * @since 2024/9/27 18:04
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Configuration
public class SiteConfig {
  @Value("${site.id}")
  private String id;

  @Value("${site.key}")
  private String key;

  @Value("${site.notifyKey}")
  private String notifyKey;
}
