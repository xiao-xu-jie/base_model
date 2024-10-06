package com.xujie.business.common.chain.register;

import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 注册处理器
 *
 * @author Xujie
 * @since 2024/10/6 10:26
 */
@Slf4j
@Component
public class RegisterChain {
  @Resource List<RegisterHandler> registerHandlers;

  @Async
  public void handle(Long userId) {
    for (RegisterHandler registerHandler : registerHandlers) {
      try {
        registerHandler.handle(userId);
      } catch (Exception e) {
        // 记录日志
        log.error("[注册后操作] 注册后处理失败日志：{}", e.getMessage());
      }
    }
  }
}
