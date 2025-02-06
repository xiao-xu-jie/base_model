package com.xujie.business.application.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
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
//    @Value("${spring.data.redis.cluster.nodes}")
//    private List<String> nodes;

    @Value("${spring.data.redis.host}")
    private String host;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + 6379);
        return Redisson.create(config);
    }
}
