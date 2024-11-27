package com.xujie.business.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Xujie
 * @since 2024/11/27 10:22
 */
public class LogInterceptor implements HandlerInterceptor {
  private static final String traceId = "traceId";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String tid = UUID.randomUUID().toString().replace("-", "");
    if (!StringUtils.isEmpty(request.getHeader("traceId"))) {
      tid = request.getHeader("traceId");
    }
    MDC.put(traceId, tid);
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      @Nullable Exception ex) {
    // 请求处理完成后,清除MDC中的traceId,以免造成内存泄漏
    MDC.remove(traceId);
  }
}
