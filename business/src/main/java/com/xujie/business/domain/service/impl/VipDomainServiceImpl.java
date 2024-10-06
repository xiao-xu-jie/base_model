package com.xujie.business.domain.service.impl;

import com.xujie.business.convert.VipConvert;
import com.xujie.business.domain.BO.BizVipBO;
import com.xujie.business.domain.service.VipDomainService;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.DO.BizUserVip;
import com.xujie.business.infra.DO.BizVip;
import com.xujie.business.infra.service.VipService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * VIP领域服务
 *
 * @author Xujie
 * @since 2024/10/6 11:10
 */
@Slf4j
@Service
public class VipDomainServiceImpl implements VipDomainService {
  @Resource private VipService vipService;
  @Resource private VipConvert vipConvert;

  /**
   * 获取VIP列表
   *
   * @return VIP列表
   */
  @Override
  public List<BizVipBO> listVip() {
    List<BizVip> bizVips = vipService.listVip();
    return vipConvert.convertDOList2BOList(bizVips);
  }

  @Override
  public BizVipBO getVipByUserId(Long userId) {
    BizUserVip userVipByEntity =
        vipService.getUserVipByEntity(BizUser.builder().id(userId).build());
    if (userVipByEntity == null) {
      return null;
    }
    BizVipBO bizVipBO = vipConvert.convertDO2BO(userVipByEntity);
    BizVip vip = vipService.getVipByEntity(BizVip.builder().id(userVipByEntity.getVipId()).build());
    bizVipBO.setVipIcon(vip.getVipIcon());
    bizVipBO.setVipImg(vip.getVipImg());

    return bizVipBO;
  }
}
