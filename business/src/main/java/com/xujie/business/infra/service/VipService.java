package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.DO.BizUserVip;

public interface VipService {
  /**
   * 赠送默认VIP
   *
   * @param userId 用户id
   */
  void sendVip(Long userId);

  /**
   * 获取用户VIP信息
   *
   * @param user 用户
   * @return 用户VIP信息
   */
  BizUserVip getUserVipByEntity(BizUser user);
}
