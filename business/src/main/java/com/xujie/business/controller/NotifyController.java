package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.common.entity.Result;
import com.xujie.business.config.SiteConfig;
import com.xujie.business.domain.service.NotifyDomainService;
import jakarta.annotation.Resource;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单回调控制器
 *
 * @author Xujie
 * @since 2024/9/27 16:49
 */
@Slf4j
@SaIgnore
@RestController
@RequestMapping("/notify")
public class NotifyController {
  @Resource private NotifyDomainService notifyDomainService;
  @Resource private SiteConfig siteConfig;

  @PostMapping
  public Result<Boolean> notify(@RequestBody Map<String, String> map) {
    String key = map.get("key");
    String orderNo = map.get("orderId");
    if (key.equals(siteConfig.getNotifyKey())) {
      notifyDomainService.handlePaySuccess(Long.valueOf(orderNo));
      return Result.okMessage("success");
    }
    log.error("notify error, key:{}, orderNo:{}", key, orderNo);
    return Result.fail("fail", null);
  }
}
