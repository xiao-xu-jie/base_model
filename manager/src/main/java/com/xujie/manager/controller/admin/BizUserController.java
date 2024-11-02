package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizUserAddReqDTO;
import com.xujie.manager.DTO.req.BizUserQueryReqDTO;
import com.xujie.manager.DTO.req.BizUserUpdateVipReqDTO;
import com.xujie.manager.DTO.res.BizUserQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.BizUserBO;
import com.xujie.manager.domain.convert.BizUserConvert;
import com.xujie.manager.domain.service.BizUserDomainService;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.DO.BizVip;
import com.xujie.manager.infra.mapper.BizVipMapper;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * (BizUser)表控制层
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@RestController
@RequestMapping("/bizUser")
public class BizUserController
    extends BaseController<
        BizUserQueryReqDTO,
        BizUserQueryResDTO,
        BizUserAddReqDTO,
        BizUserBO,
        BizUser,
        BizUserConvert,
        BizUserDomainService> {

  public BizUserController(BizUserConvert baseConvert, BizUserDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }

  @Resource private BizVipMapper bizVipMapper;
  @Resource private BizUserDomainService bizUserDomainService;

  /**
   * 获取会员列表
   *
   * @return
   */
  @GetMapping("/getVipList")
  public Result<List<BizVip>> getVipList() {
    List<BizVip> list = bizVipMapper.selectList(null);
    return Result.ok(list);
  }

  @PutMapping("/updateUserVip")
  public Result<Boolean> updateUserVip(@RequestBody BizUserUpdateVipReqDTO bizUserUpdateVipReqDTO) {
    bizUserDomainService.updateUserVip(
        bizUserUpdateVipReqDTO.getId(), bizUserUpdateVipReqDTO.getVipId());
    return Result.ok(true);
  }
}
