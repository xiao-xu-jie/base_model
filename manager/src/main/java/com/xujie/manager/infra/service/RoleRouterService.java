package com.xujie.manager.infra.service;

import com.xujie.manager.infra.DO.SysRoleRouter;

import java.util.List;

public interface RoleRouterService {
    void addRoleRouter(List<Long> routerIds, Long roleId);
    void deleteRoleRouterByRoleId(Long roleId);
    List<SysRoleRouter> getRoleRouterByRoleId(Long roleId);

    List<SysRoleRouter> getRoleRouterByRoleCode(List<String> roleList);
}
