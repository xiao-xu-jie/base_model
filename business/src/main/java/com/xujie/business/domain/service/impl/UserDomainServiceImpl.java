package com.xujie.business.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.utils.SMSUtil;
import com.xujie.business.convert.UserConvert;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.domain.service.UserDomainService;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.service.UserService;
import com.xujie.tools.ConditionCheck;
import com.xujie.wx.utils.WxAppUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户领域服务实现
 *
 * @author Xujie
 * @since 2024/10/5 15:02
 */
@Slf4j
@Service
public class UserDomainServiceImpl implements UserDomainService {
  @Resource private UserService userService;
  @Resource private SMSUtil smsUtil;
  @Resource private UserConvert userConvert;
  @Resource private WxAppUtil wxAppUtil;

  /**
   * 通过手机号登录
   *
   * @param phone 用户手机号
   * @param code 验证码
   * @return 用户带Token信息
   */
  @Override
  public BizUserBO loginByPhone(String phone, String code) {
    BizUser userByEntity = userService.getUserByEntity(BizUser.builder().phone(phone).build());
    ConditionCheck.nullAndThrow(userByEntity, new CustomException("用户不存在"));
    smsUtil.checkCode(phone, code);
    StpUtil.login(userByEntity.getId());
    return userConvert.convertDO2BO(userByEntity);
  }

  /**
   * 通过微信登录
   *
   * @param code 微信code
   * @return 用户带Token信息
   */
  @Override
  public BizUserBO loginByWx(String code) {

    return null;
  }
}
