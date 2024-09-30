package com.xujie.business.common.filters;

import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.*;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Xujie
 * @since 2024/9/30 08:27
 */
@Slf4j
@Component
public class RateLimiterFilter implements Filter {
  private RateLimiter rateLimiter = RateLimiter.create(100);

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    rateLimiter.acquire();
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
