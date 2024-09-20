package com.xujie.manager.controller;

import com.xujie.manager.DTO.res.RoleQueryDTO;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.domain.convert.RoleConvert;
import com.xujie.manager.domain.service.RoleDomainService;
import jakarta.annotation.Resource;
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
public class RoleController {
    @Resource
    private RoleDomainService roleDomainService;
    @Resource
    private RoleConvert roleConvert;

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @GetMapping("/list")
    public Result<List<RoleQueryDTO>> list() {
        List<RoleBO> allRoleList = roleDomainService.getAllRoleList();
        return Result.ok(roleConvert.convertListBO2DTO(allRoleList));
    }
}
