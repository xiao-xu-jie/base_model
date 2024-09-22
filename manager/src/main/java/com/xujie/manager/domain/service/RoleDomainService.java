package com.xujie.manager.domain.service;

import com.xujie.manager.common.base.service.BaseDomainService;
import com.xujie.manager.domain.BO.RoleBO;

import java.util.List;


public interface RoleDomainService extends BaseDomainService<RoleBO> {
    List<RoleBO> getAllRoleList();

    List<Long> getRoutersByRoleId(Long roleId);

}
