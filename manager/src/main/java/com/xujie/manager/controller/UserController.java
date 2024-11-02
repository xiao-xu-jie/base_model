package com.xujie.manager.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.xujie.manager.DTO.req.UserAddReqDTO;
import com.xujie.manager.DTO.req.UserQueryReqDTO;
import com.xujie.manager.DTO.res.UserQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.domain.convert.UserConvert;
import com.xujie.manager.domain.service.UserDomainService;
import com.xujie.manager.infra.DO.SysUser;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Xujie
 * @since 2024/9/19 10:39
 */
@RestController
@RequestMapping("/user")
public class UserController
    extends BaseController<
        UserQueryReqDTO,
        UserQueryResDTO,
        UserAddReqDTO,
        UserBO,
        SysUser,
        UserConvert,
        UserDomainService> {

  public UserController(UserConvert baseConvert, UserDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }

  /**
   * 获取用户角色
   *
   * @param id 用户id
   * @return 用户角色
   */
  @GetMapping("/getUserRole")
  @SaCheckRole("admin")
  public Result<List<String>> getUserRole(@RequestParam(name = "id", required = true) Long id) {

    List<String> list = baseDomainService.getUserRoleList(id);
    return Result.ok(list);
  }
}
