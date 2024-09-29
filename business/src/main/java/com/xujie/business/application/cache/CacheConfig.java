package com.xujie.business.application.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Xujie
 * @since 2024/9/20 22:34
 */
@EnableCaching
@Configuration
public class CacheConfig {
  /**
   * 配置 cacheManager 代替默认的cacheManager （缓存管理器）
   *
   * @param factory RedisConnectionFactory
   * @return CacheManager
   */
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {
    RedisSerializer<String> redisSerializer = new StringRedisSerializer();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //        //仅仅序列化对象的属性，且属性不可为final修饰
    objectMapper.activateDefaultTyping(
        LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.PROPERTY);
    Jackson2JsonRedisSerializer serializer =
        new Jackson2JsonRedisSerializer(objectMapper, Object.class);
    // 配置key value序列化
    RedisCacheConfiguration config =
        RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer))
            // 关闭控制存储
            .disableCachingNullValues()
            // 修改前缀与key的间隔符号，默认是::
            .computePrefixWith(cacheName -> cacheName + ":");

    // 设置特有的Redis配置
    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
    // 定制化的Cache 设置过期时间 eg:以role：开头的缓存存活时间为30s
    cacheConfigurations.put("class", customRedisCacheConfiguration(config, Duration.ofSeconds(30)));
    cacheConfigurations.put(
        "categoryGood", customRedisCacheConfiguration(config, Duration.ofSeconds(60)));
    cacheConfigurations.put("order", customRedisCacheConfiguration(config, Duration.ofSeconds(30)));
    // 构建redis缓存管理器
    RedisCacheManager cacheManager =
        RedisCacheManager.builder(factory)
            // Cache事务支持
            .transactionAware()
            .withInitialCacheConfigurations(cacheConfigurations)
            .cacheDefaults(config)
            .build();
    // 设置过期时间
    return cacheManager;
  }

  /**
   * 设置RedisConfiguration配置
   *
   * @param config
   * @param ttl
   * @return
   */
  public RedisCacheConfiguration customRedisCacheConfiguration(
      RedisCacheConfiguration config, Duration ttl) {
    // 设置缓存缺省超时时间
    return config.entryTtl(ttl);
  }
}
