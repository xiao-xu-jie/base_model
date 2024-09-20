package com.xujie.manager.infra.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.SysRole;

import java.util.List;

public interface RoleService {
    boolean addRole(SysRole role);

    boolean deleteRole(Long id);

    boolean updateRole(Long id, SysRole role);

    List<SysRole> getAllRoleByEntity(SysRole role);

    List<SysRole> getAllRoleByCodes(List<String> codes);

    SysRole getOneRoleByEntity(SysRole role);

    Page<SysRole> getRolePageList(SysRole sysRole, Integer pageNum, Integer pageSize);

}
