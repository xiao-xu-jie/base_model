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
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class SaTokenConfigure {

    private final Environment env;
    private final SaTokenFilterProperties filterProperties;

    /**
     * 注册 [Sa-Token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        SaServletFilter filter = new SaServletFilter();

        // 指定拦截路由
        filterProperties.getInclude().forEach(filter::addInclude);

        // 指定放行路由
        filterProperties.getExclude().forEach(filter::addExclude);

        // 设置认证函数
        filter.setAuth(obj -> {
            // 登录认证 -- 拦截所有路由，并排除配置的放行路由
            SaRouter.match("/**")
                    .notMatch(filterProperties.getAuthExclude())
                    .check(() -> StpUtil.checkLogin());
        });

        // 异常处理函数
        filter.setError(e -> {
            if (e instanceof NotLoginException) {
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
        });

        // 前置函数
        filter.setBeforeAuth(r -> {
            SaHolder.getResponse()
                    .setServer(env.getProperty("spring.application.name"));
        });

        return filter;
    }
}
