package com.xujie.business;

import com.xujie.business.application.redis.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Xujie
 * @since 2024/9/14 14:45
 **/

@SpringBootTest(classes = businessApplication.class)
public class TestRedis {

    @Test
    public void test() {
        RedisUtils.setCacheObject("test", "test");
        Optional<String> test = RedisUtils.getCacheObject("test");
        System.out.println(test.orElse("null"));
    }

    @Test
    public void testZset() {
        Set<ZSetOperations.TypedTuple<Object>> set = new HashSet<>();
        ZSetOperations.TypedTuple<Object> typedTuple = new DefaultTypedTuple<>("test", 1.0);
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<>("test2", 2.0);
        ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<>("test3", 5.0);
        set.add(typedTuple);
        set.add(typedTuple1);
        set.add(typedTuple2);
        RedisUtils.zAdd("rank", set);
        Set<ZSetOperations.TypedTuple<Object>> rank = RedisUtils.zGet("rank", 0, 2);
        rank.forEach(System.out::println);
    }

    @Test
    public void testZsetRemove() {
        RedisUtils.zRemoveRangeByScore("rank", 1, 2);
        Set<ZSetOperations.TypedTuple<Object>> rank = RedisUtils.zGet("rank", 0, 5);
        rank.forEach(System.out::println);
    }
}
