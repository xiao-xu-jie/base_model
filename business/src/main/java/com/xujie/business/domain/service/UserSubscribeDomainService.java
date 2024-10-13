package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizUserSubscribeBO;
import java.util.List;

/** 用户订阅领域服务 */
public interface UserSubscribeDomainService {

  /**
   * 订阅
   *
   * @param userId 用户ID
   * @param subUserId 订阅的用户ID
   */
  void subscribe(Long userId, Long subUserId);

  /**
   * 取消订阅
   *
   * @param userId 用户ID
   * @param subUserId 订阅的用户ID
   */
  void cancelSubscribe(Long userId, Long subUserId);

  /**
   * 是否订阅
   *
   * @param userId 用户ID
   * @param subUserId 订阅的用户ID
   * @return 是否订阅
   */
  Boolean isSubscribe(Long userId, Long subUserId);

  /**
   * 获取订阅列表
   *
   * @param userId 用户ID
   * @return 订阅列表
   */
  List<BizUserSubscribeBO> getSubscribeList(Long userId);

  List<BizUserSubscribeBO> getSubscribeMyList(Long userId);
}
