package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizVipBO;
import java.util.List;

public interface VipDomainService {

  /**
   * 获取VIP列表
   *
   * @return VIP列表
   */
  List<BizVipBO> listVip();

  /**
   * 通过用户ID获取VIP信息
   *
   * @param userId 用户ID
   * @return VIP信息
   */
  BizVipBO getVipByUserId(Long userId);
}
