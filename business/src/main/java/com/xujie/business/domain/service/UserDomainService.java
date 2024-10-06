package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizUserBO;

public interface UserDomainService {
  /**
   * 通过手机号登录
   *
   * @param phone 手机号
   * @param code 验证码
   * @return 用户信息
   */
  BizUserBO loginByPhone(String phone, String code);

  /**
   * 通过微信登录
   *
   * @param code 微信code
   * @return 用户信息
   */
  BizUserBO loginByWx(String code);

  /**
   * 发送验证码
   *
   * @param phone 手机号
   * @return 是否发送成功
   */
  Boolean sendCode(String phone);

  BizUserBO register(BizUserBO userBO, String code);
}
