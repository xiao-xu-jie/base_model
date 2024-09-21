package com.xujie.manager.infra.service;

import com.xujie.manager.common.base.service.BaseService;
import com.xujie.manager.infra.DO.SysRole;

import java.util.List;

public interface RoleService extends BaseService<SysRole> {
    List<SysRole> getAllRoleByCodes(List<String> codes);

}
