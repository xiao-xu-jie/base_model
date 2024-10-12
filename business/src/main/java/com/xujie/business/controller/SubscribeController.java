package com.xujie.business.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.xujie.business.common.entity.Result;
import com.xujie.business.domain.service.UserSubscribeDomainService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订阅控制器
 *
 * @author Xujie
 * @since 2024/10/12 17:06
 */
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
  @Resource private UserSubscribeDomainService userSubscribeDomainService;

  /**
   * 订阅
   *
   * @param subUserId 订阅的用户ID
   */
  @PostMapping("/subscribe")
  public Result<Boolean> subscribe(@RequestParam("subUserId") Long subUserId) {
    userSubscribeDomainService.subscribe(StpUtil.getLoginIdAsLong(), subUserId);
    return Result.ok(Boolean.TRUE);
  }

  /**
   * 取消订阅
   *
   * @param subUserId 取消订阅的用户ID
   */
  @PostMapping("/cancelSubscribe")
  public Result<Boolean> cancelSubscribe(@RequestParam("subUserId") Long subUserId) {
    userSubscribeDomainService.cancelSubscribe(StpUtil.getLoginIdAsLong(), subUserId);
    return Result.ok(Boolean.TRUE);
  }
}
