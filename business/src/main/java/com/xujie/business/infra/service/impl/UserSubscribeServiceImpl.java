package com.xujie.business.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.DO.BizUserSubscribe;
import com.xujie.business.infra.mapper.BizUserMapper;
import com.xujie.business.infra.mapper.BizUserSubscribeMapper;
import com.xujie.business.infra.service.UserSubscribeService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户订阅服务实现
 *
 * @author Xujie
 * @since 2024/10/12 17:10
 */
@Slf4j
@Service
public class UserSubscribeServiceImpl implements UserSubscribeService {
  @Resource private BizUserSubscribeMapper userSubscribeMapper;
  @Resource private BizUserMapper userMapper;

  @Override
  public void addSubscribe(Long userId, Long subUserId) {
    log.info("用户订阅，userId：{}，subUserId：{}", userId, subUserId);
    ConditionCheck.trueAndThrow(isSubscribe(userId, subUserId), new CustomException("已订阅，请勿重复订阅"));
    BizUser bizUser = userMapper.selectById(subUserId);
    ConditionCheck.nullAndThrow(bizUser, new CustomException("非法请求"));
    userSubscribeMapper.insert(
        BizUserSubscribe.builder().userId(userId).subUserId(subUserId).build());
  }

  @Override
  public void cancelSubscribe(Long userId, Long subUserId) {
    log.info("取消订阅，userId：{}，subUserId：{}", userId, subUserId);
    userSubscribeMapper.deleteByMap(Map.of("user_id", userId, "sub_user_id", subUserId));
  }

  @Override
  public Boolean isSubscribe(Long userId, Long subUserId) {
    LambdaQueryWrapper<BizUserSubscribe> eq =
        Wrappers.<BizUserSubscribe>lambdaQuery()
            .eq(BizUserSubscribe::getUserId, userId)
            .eq(BizUserSubscribe::getSubUserId, subUserId);
    return userSubscribeMapper.selectCount(eq) > 0;
  }

  @Override
  public List<BizUserSubscribe> getSubscribedUsers(Long userId) {
    LambdaQueryWrapper<BizUserSubscribe> eq =
        Wrappers.<BizUserSubscribe>lambdaQuery().eq(BizUserSubscribe::getUserId, userId);
    return userSubscribeMapper.selectList(eq);
  }
}
