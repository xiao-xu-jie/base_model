package com.xujie.manager.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.RoleBO;

import java.util.List;


public interface RoleDomainService {
    Page<RoleBO> getRolePageList(RoleBO roleBO, Integer pageNum, Integer pageSize);

    List<RoleBO> getAllRoleList();
}
