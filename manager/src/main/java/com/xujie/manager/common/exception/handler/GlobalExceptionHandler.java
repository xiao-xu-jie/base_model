package com.xujie.manager.common.exception.handler;


import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.common.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Set;

/**
 * @Author: Xujie
 * @Date: 2024/7/15 21:59
 * @Description:
 **/
@RestControllerAdvice(basePackages = "com.xujie.manager")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BaseException.class})
    public Result<Boolean> customExceptionHandler(BaseException e) {
        return Result.fail(e, null);
    }


    @ExceptionHandler({ConstraintViolationException.class, BindException.class, MethodArgumentNotValidException.class})
    public Result<Boolean> validateException(Exception ex, HttpServletRequest request) {
//        ex.printStackTrace();
        String msg = null;
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException =
                    (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations =
                    constraintViolationException.getConstraintViolations();
            ConstraintViolation<?> next = violations.iterator().next();
            msg = next.getMessage();
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            msg = bindException.getBindingResult().getFieldError().getDefaultMessage();
        }
        return Result.fail(msg, null);
    }


    @ExceptionHandler({RuntimeException.class})
    public Result<Boolean> exceptionHandler(Exception e) {
        log.error("系统异常", e);
        int length = Math.min(e.getMessage().length(), 10);
        return Result.fail(e.getMessage().substring(0, length), Arrays.toString(e.getStackTrace()).substring(1, 10));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public Result<Boolean> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("参数异常", e);
        return Result.fail(e.getMessage(), null);
    }

    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<Boolean> handlerException(NotLoginException e) {

        // 打印堆栈，以供调试
        e.printStackTrace();

        // 返回给前端
        return Result.fail(e.getMessage(), null);
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public Result<Boolean> handlerException(NotPermissionException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage(), null);
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public Result<Boolean> handlerException(NotRoleException e) {
        e.printStackTrace();
        return Result.fail("缺少角色：" + e.getRole(),null);
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public Result<Boolean> handlerException(NotSafeException e) {
        e.printStackTrace();
        return Result.fail("二级认证校验失败：" + e.getService(),null);
    }

    // 拦截：服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public Result<Boolean> handlerException(DisableServiceException e) {
        e.printStackTrace();
        return Result.fail("当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封",null);
    }


}
