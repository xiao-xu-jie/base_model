package com.xujie.manager.controller;

import com.xujie.manager.DTO.req.RoleAddReqDTO;
import com.xujie.manager.DTO.req.RoleQueryReqDTO;
import com.xujie.manager.DTO.res.RoleQueryResDTO;
import com.xujie.manager.DTO.res.RouterResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.domain.convert.RoleConvert;
import com.xujie.manager.domain.convert.RoutersConvert;
import com.xujie.manager.domain.service.RoleDomainService;
import com.xujie.manager.infra.DO.SysRole;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制器
 *
 * @author Xujie
 * @since 2024/9/20 21:54
 */
@RestController
@RequestMapping("/role")
public class RoleController
    extends BaseController<
        RoleQueryReqDTO,
        RoleQueryResDTO,
        RoleAddReqDTO,
        RoleBO,
        SysRole,
        RoleConvert,
        RoleDomainService> {

  @Resource private RoutersConvert routersConvert;

  public RoleController(RoleConvert baseConvert, RoleDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }

  /**
   * 查询所有角色
   *
   * @return 角色列表
   */
  @GetMapping("/list")
  public Result<List<RoleQueryResDTO>> list() {
    List<RoleBO> allRoleList = baseDomainService.getAllRoleList();
    return Result.ok(baseConvert.convertListBO2DTO(allRoleList));
  }

  /**
   * 根据角色id获取路由
   *
   * @param roleId 角色id
   * @return 路由id列表
   */
  @GetMapping("/getRoutersByRoleId")
  public Result<List<Long>> getRoutersByRoleId(
      @RequestParam(value = "roleId", required = false) Long roleId) {
    return Result.ok(baseDomainService.getRoutersByRoleId(roleId));
  }

  @GetMapping("/getAllRouters")
  public Result<List<RouterResDTO>> getAllRouters() {
    return Result.ok(routersConvert.convertListBO2DTO2(baseDomainService.getAllRouters()));
  }
}
