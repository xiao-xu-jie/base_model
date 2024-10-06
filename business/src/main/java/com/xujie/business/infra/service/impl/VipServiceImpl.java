package com.xujie.business.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.business.common.enums.VipExpireStatusEnum;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.DO.BizUserVip;
import com.xujie.business.infra.DO.BizVip;
import com.xujie.business.infra.mapper.BizUserVipMapper;
import com.xujie.business.infra.mapper.BizVipMapper;
import com.xujie.business.infra.service.UserService;
import com.xujie.business.infra.service.VipService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * VIP服务实现
 *
 * @author Xujie
 * @since 2024/10/6 10:33
 */
@Slf4j
@Service
public class VipServiceImpl implements VipService {
  @Resource private UserService userService;
  @Resource private BizVipMapper vipMapper;
  @Resource private BizUserVipMapper userVipMapper;

  /**
   * 赠送默认VIP
   *
   * @param userId 用户id
   */
  @Override
  public void sendVip(Long userId) {
    log.info("[VIP服务][赠送VIP] 用户{}赠送VIP", userId);
    BizUser userByEntity = userService.getUserByEntity(BizUser.builder().id(userId).build());
    if (userByEntity == null) {
      log.error("[VIP服务][赠送VIP] 用户{}不存在", userId);
      return;
    }
    LambdaQueryWrapper<BizUserVip> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper
        .eq(BizUserVip::getUserId, userId)
        .eq(BizUserVip::getIsExpire, VipExpireStatusEnum.NOT_EXPIRE);
    if (userVipMapper.selectCount(queryWrapper) > 0) {
      log.error("[VIP服务][赠送VIP] 用户{}已经是VIP", userId);
      return;
    }
    // 获取默认赠送的VIP
    BizVip defaultVip =
        vipMapper.selectOne(new LambdaQueryWrapper<BizVip>().eq(BizVip::getIsDefault, true));
    if (defaultVip == null) {
      log.error("[VIP服务][赠送VIP] 默认VIP不存在");
      return;
    }
    try {
      BizUserVip userVip =
          BizUserVip.builder()
              .userId(userId)
              .vipId(defaultVip.getId())
              .vipName(defaultVip.getVipName())
              .expireTime(DateTime.now().plusDays(defaultVip.getVipEffectiveDay()).toDate())
              .isExpire(VipExpireStatusEnum.NOT_EXPIRE)
              .postCount(defaultVip.getVipPostCount())
              .notifyCount(defaultVip.getVipNotifyCount())
              .build();
      userVipMapper.insert(userVip);
    } catch (Exception e) {
      log.error("[VIP服务][赠送VIP] 赠送VIP失败", e);
      return;
    }
  }

  @Override
  public BizUserVip getUserVipByEntity(BizUser user) {
    // 查询用户
    user = userService.getUserByEntity(user);
    ConditionCheck.nullAndThrow(user, new CustomException("用户不存在"));
    // 查询用户VIP
    return userVipMapper.selectOne(
        new LambdaQueryWrapper<BizUserVip>().eq(BizUserVip::getUserId, user.getId()));
  }
}
