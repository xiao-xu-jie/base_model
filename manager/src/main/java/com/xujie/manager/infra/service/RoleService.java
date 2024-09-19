package com.xujie.manager.infra.service;

import com.xujie.manager.infra.DO.SysRole;

import java.util.List;

public interface RoleService {
    boolean addRole(SysRole role);

    boolean deleteRole(Long id);

    boolean updateRole(Long id, SysRole role);

    List<SysRole> getAllRoleByEntity(SysRole role);

    SysRole getOneRoleByEntity(SysRole role);
}
