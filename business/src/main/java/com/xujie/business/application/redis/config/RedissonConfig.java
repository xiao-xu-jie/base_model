package com.xujie.business.application.redis.config;

import java.util.List;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置类
 *
 * @author Xujie
 * @since 2024/10/26 13:40
 */
@Configuration
public class RedissonConfig {
  @Value("${spring.data.redis.cluster.nodes}")
  private List<String> nodes;

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    ClusterServersConfig clusterServersConfig = config.useClusterServers();
    clusterServersConfig.setPassword("xujie520");
    clusterServersConfig.setScanInterval(2000);
    for (String node : nodes) {
      clusterServersConfig.addNodeAddress("redis://" + node);
    }
    return Redisson.create(config);
  }
}
