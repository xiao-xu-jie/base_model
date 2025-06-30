package com.xujie.business.commom.security.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SaTokenConfigure {

    @Resource
    private Environment env;

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()

                // 指定 拦截路由 与 放行路由
                .addInclude("/**").addExclude("/test/**", "/ai/chat/upload")    /* 排除掉 /favicon.ico */

                // 认证函数: 每次请求执行 
                .setAuth(obj -> {

                    // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录 
                    SaRouter.match("/**", "/user/doLogin", () -> StpUtil.checkLogin());

                    // 更多拦截处理方式，请参考“路由拦截式鉴权”章节 */
                })

                // 异常处理函数：每次认证函数发生异常时执行此函数 
                .setError(e -> {
                    if (e instanceof NotLoginException) {
                        // 返回给前端的数据
                        return SaResult.error("当前会话未登录").setCode(401);
                    }
                    if (e instanceof NotRoleException) {
                        return SaResult.error("没有权限访问").setCode(403);
                    }
                    if (e instanceof NotPermissionException) {
                        return SaResult.error("没有权限访问").setCode(403);
                    }
                    if (e instanceof DisableServiceException) {
                        return SaResult.error("账号已被封禁").setCode(403);
                    }
                    return SaResult.error(e.getMessage()).setCode(409);
                })

                // 前置函数：在每次认证函数之前执行（BeforeAuth 不受 includeList 与 excludeList 的限制，所有请求都会进入）
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer(env.getProperty("spring.application.name"))
                    ;
                })
                ;
    }

}