package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.DTO.res.vip.VipQueryResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.VipConvert;
import com.xujie.business.domain.BO.BizVipBO;
import com.xujie.business.domain.service.VipDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员控制器
 *
 * @author Xujie
 * @since 2024/10/6 11:02
 */
@RestController
@RequestMapping("/vip")
public class VipController {
  @Resource private VipDomainService vipDomainService;
  @Resource private VipConvert vipConvert;

  /**
   * 获取会员列表
   *
   * @return 会员列表
   */
  @SaIgnore
  @GetMapping("/list")
  public Result<List<VipQueryResDTO>> listVip() {
    List<BizVipBO> bizVipBOS = vipDomainService.listVip();
    return Result.ok(vipConvert.convertBOList2ResDTOList(bizVipBOS));
  }
}
