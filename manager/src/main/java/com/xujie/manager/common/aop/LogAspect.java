package com.xujie.manager.common.aop;

import com.google.gson.Gson;
import com.xujie.manager.common.annotations.WebLog;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 * @author Xujie
 * @since 2024/9/23 23:42
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.xujie.manager.common.annotations.WebLog)")
    public void webLog(){
    }
    @Resource(name = "logExecutor")
    private ThreadPoolTaskExecutor logExecutor;

    @Around("webLog()")
    public Object  around(ProceedingJoinPoint proceedingJoinPoint){
        Signature signature = proceedingJoinPoint.getSignature();
        try {
            getAspectLogDescription(proceedingJoinPoint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        // 打印入参
        Object[] args = proceedingJoinPoint.getArgs();
        String request = new Gson().toJson(args);
        String response = new Gson().toJson(result);
        long endTime = System.currentTimeMillis();
        logExecutor.execute(() -> {
            log.info("request: {}", request);
            log.info("response: {}", response);
            log.info("time: {}", endTime - startTime);
        });
        return result;

    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public WebLog getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    return method.getAnnotation(WebLog.class);
                }
            }
        }
        return null;
    }

    class LogTask implements Runnable{


        @Override
        public void run() {

        }
    }
}
