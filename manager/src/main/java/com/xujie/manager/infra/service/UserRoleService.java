package com.xujie.manager.infra.service;

import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.DO.SysUserRole;

import java.util.List;

public interface UserRoleService {
    boolean addUserRole(Long userId, Long roleId);

    boolean deleteUserRole(Long userId, Long roleId);

    List<String> getRoleList(Long loginId);

    List<SysRole> getRoleListByUserId(Long userId);
    List<SysUserRole> getUserListByRoleId(Long[] roleIds);

    void saveUserRole(Long id, List<String> roles);
}
