package com.xujie.business.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.xujie.business.DTO.res.user.UserSubResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.UserConvert;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.domain.service.UserSubscribeDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.*;

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
  @Resource private UserConvert userConvert;

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

  /**
   * 获取我的订阅列表
   *
   * @return 我的订阅列表
   */
  @GetMapping("/getMySubscribeList")
  public Result<List<UserSubResDTO>> getMySubscribeList() {
    List<BizUserBO> subscribeList =
        userSubscribeDomainService.getSubscribeList(StpUtil.getLoginIdAsLong());
    return Result.ok(userConvert.convertBOList2SubResDTOList(subscribeList));
  }
}
