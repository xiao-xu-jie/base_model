package com.xujie.business.common.aop;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.xujie.business.application.redis.utils.RedisUtils;
import com.xujie.business.common.annotations.MyCache;
import com.xujie.business.common.exception.CustomException;
import jakarta.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * 缓存切面
 *
 * @author Xujie
 * @since 2024/10/27 10:59
 */
@Aspect
@Component
public class CacheAop {

  @Resource private RedissonClient redissonClient;
  private final String LOCK_PREFIX_KEY = "lock:";

  @Pointcut("@annotation(com.xujie.business.common.annotations.MyCache)")
  public void pointcut() {}

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {

    Method targetMethod = getTargetMethod(joinPoint);
    Class<?> returnType = targetMethod.getReturnType();
    MyCache cacheAnnotation = targetMethod.getAnnotation(MyCache.class);
    String key = cacheAnnotation.key();
    Object[] args = joinPoint.getArgs();
    String hashKey = getHashKey(args);
    Optional<Object> cacheObject = RedisUtils.getCacheObject(key + ":" + hashKey);
    if (cacheObject.isPresent()) {
      return JSON.parseObject(cacheObject.get().toString(), returnType);
    }

    RLock lock = redissonClient.getLock(LOCK_PREFIX_KEY + hashKey);
    lock.lock();
    cacheObject = RedisUtils.getCacheObject(key + ":" + hashKey);
    if (cacheObject.isPresent()) {
      return JSON.parseObject(cacheObject.get().toString(), returnType);
    }

    Object proceed = null;
    try {
      proceed = joinPoint.proceed();
      RedisUtils.setCacheObject(
          key + ":" + hashKey,
          JSON.toJSONString(proceed),
          cacheAnnotation.expire(),
          cacheAnnotation.timeUnit());
    } catch (Throwable e) {
      throw new CustomException(e.getMessage());
    } finally {
      lock.unlock();
    }
    return proceed;
  }

  public MyCache getCacheAnnotation(ProceedingJoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    Method[] declaredMethods = signature.getDeclaringType().getDeclaredMethods();
    String name = signature.getName();
    for (Method method : declaredMethods) {
      if (method.getName().equals(name)) {
        return method.getAnnotation(MyCache.class);
      }
    }
    return null;
  }

  /** 获取目标方法 */
  private Method getTargetMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
    Signature signature = pjp.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method agentMethod = methodSignature.getMethod();
    return pjp.getTarget()
        .getClass()
        .getMethod(agentMethod.getName(), agentMethod.getParameterTypes());
  }

  private String getHashKey(Object[] objs) {
    StringBuilder q = new StringBuilder();
    Arrays.stream(objs).map(Object::toString).forEach(q::append);
    return DigestUtil.md5Hex(q.toString());
  }
}
