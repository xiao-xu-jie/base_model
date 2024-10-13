package com.xujie.business.domain.service.impl;

import com.xujie.business.convert.QuotationConvert;
import com.xujie.business.domain.BO.BizUserSubscribeBO;
import com.xujie.business.domain.service.UserSubscribeDomainService;
import com.xujie.business.infra.DO.BizUserSubscribe;
import com.xujie.business.infra.service.UserSubscribeService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户订阅领域服务实现
 *
 * @author Xujie
 * @since 2024/10/12 17:09
 */
@Slf4j
@Service
public class UserSubscribeDomainServiceImpl implements UserSubscribeDomainService {
  @Resource private UserSubscribeService userSubscribeService;
  @Resource private QuotationConvert quotationConvert;

  /**
   * 订阅
   *
   * @param userId 用户ID
   * @param subUserId 订阅的用户ID
   */
  @Override
  public void subscribe(Long userId, Long subUserId) {
    ConditionCheck.trueAndThrow(userId.equals(subUserId), new IllegalArgumentException("不能订阅自己"));
    userSubscribeService.addSubscribe(userId, subUserId);
  }

  /**
   * 取消订阅
   *
   * @param userId 用户ID
   * @param subUserId 订阅的用户ID
   */
  @Override
  public void cancelSubscribe(Long userId, Long subUserId) {
    userSubscribeService.cancelSubscribe(userId, subUserId);
  }

  /**
   * 是否订阅
   *
   * @param userId 用户ID
   * @param subUserId 订阅的用户ID
   * @return 是否订阅
   */
  @Override
  public Boolean isSubscribe(Long userId, Long subUserId) {
    return userSubscribeService.isSubscribe(userId, subUserId);
  }

  @Override
  public List<BizUserSubscribeBO> getSubscribeList(Long userId) {
    List<BizUserSubscribe> subscribedUsers = userSubscribeService.getSubscribedUsers(userId);
    return quotationConvert.convertUserSubList2BOList(subscribedUsers);
  }

  @Override
  public List<BizUserSubscribeBO> getSubscribeMyList(Long userId) {
    List<BizUserSubscribe> subscribeMyUsers = userSubscribeService.getSubscribeMyUsers(userId);
    return quotationConvert.convertUserSubList2BOList(subscribeMyUsers);
  }
}
