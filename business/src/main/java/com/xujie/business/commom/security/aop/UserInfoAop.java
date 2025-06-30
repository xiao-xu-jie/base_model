package com.xujie.business.commom.security.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Xujie
 * @since 2025/4/25 23:56
 **/
@Aspect
@Component
public class UserInfoAop {

    @Around("@annotation(com.xujie.business.commom.security.annotations.UserInfo)")
    public Object handleMyAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        // 找到对应用户的参数
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Object) {
                args[i] = getUserInfoData();
            }
        }
        Object result;
        try {
            result = joinPoint.proceed(args);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private Object getUserInfoData() {
        // 模拟获取用户信息

        return null;
    }
}
