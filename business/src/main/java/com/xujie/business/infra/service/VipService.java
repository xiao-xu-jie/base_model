package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.DO.BizUserVip;
import com.xujie.business.infra.DO.BizVip;
import java.util.List;

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

  /**
   * 获取VIP列表
   *
   * @return VIP列表
   */
  List<BizVip> listVip();

  /**
   * 通过实体获取VIP
   *
   * @param build 实体
   * @return VIP
   */
  BizVip getVipByEntity(BizVip build);
}
