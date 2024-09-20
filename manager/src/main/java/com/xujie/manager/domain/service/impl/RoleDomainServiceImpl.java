package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.domain.convert.RoleConvert;
import com.xujie.manager.domain.service.RoleDomainService;
import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/20 21:48
 **/

@Slf4j
@Service
public class RoleDomainServiceImpl implements RoleDomainService {
    @Resource
    private RoleService baseService;
    @Resource
    private RoleConvert roleConvert;

    @Override
    public Page<RoleBO> getRolePageList(RoleBO roleBO, Integer pageNum, Integer pageSize) {

        Page<SysRole> rolePageList = baseService.getRolePageList(roleConvert.convertBO2DO(roleBO), pageNum, pageSize);

        return roleConvert.convertPageDO2BO(rolePageList);
    }

    @Override
    public List<RoleBO> getAllRoleList() {
        List<SysRole> allRoleByEntity = baseService.getAllRoleByEntity(SysRole.builder().build());
        List<RoleBO> list = roleConvert.convertDO2BO(allRoleByEntity);
        return list;
    }
}
