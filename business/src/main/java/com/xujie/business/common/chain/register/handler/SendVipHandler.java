package com.xujie.business.common.chain.register.handler;

import com.xujie.business.common.chain.register.RegisterHandler;
import com.xujie.business.infra.service.VipService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 注册后赠送VIP
 *
 * @author Xujie
 * @since 2024/10/6 10:28
 */
@Slf4j
@Component
public class SendVipHandler implements RegisterHandler {
  @Resource private VipService vipService;

  @Override
  public void handle(Long userId) {
    log.info("[注册后操作][赠送VIP] 用户{}赠送VIP", userId);
    vipService.sendVip(userId);
  }
}
