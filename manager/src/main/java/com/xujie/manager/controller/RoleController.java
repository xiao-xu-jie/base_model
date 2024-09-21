package com.xujie.manager.controller;


import com.xujie.manager.DTO.req.RoleAddReqDTO;
import com.xujie.manager.DTO.req.RoleQueryReqDTO;
import com.xujie.manager.DTO.res.RoleQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.domain.convert.RoleConvert;
import com.xujie.manager.domain.service.RoleDomainService;
import com.xujie.manager.infra.DO.SysRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 *
 * @author Xujie
 * @since 2024/9/20 21:54
 **/

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<RoleQueryReqDTO, RoleQueryResDTO, RoleAddReqDTO, RoleBO, SysRole, RoleConvert, RoleDomainService> {

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


}
