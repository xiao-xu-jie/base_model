package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.domain.convert.RoleConvert;
import com.xujie.manager.domain.service.RoleDomainService;
import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.service.RoleService;
import com.xujie.tools.ConditionCheck;
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
    public List<RoleBO> getAllRoleList() {
        List<SysRole> allRoleByEntity = baseService.getListByEntity(SysRole.builder().build());
        List<RoleBO> list = roleConvert.convertListDO2BO(allRoleByEntity);
        return list;
    }

    @Override
    public void add(RoleBO roleBO) {
        Long id = baseService.addOne(roleConvert.convertBO2DO(roleBO));
        ConditionCheck.nullAndThrow(id, new CustomException("添加角色失败"));
    }

    @Override
    public Page<RoleBO> getPageList(RoleBO roleBO, Integer pageNum, Integer pageSize) {
        Page<SysRole> rolePageList = baseService.getPageList(roleConvert.convertBO2DO(roleBO), pageNum, pageSize);
        return roleConvert.convertPageDO2BO(rolePageList);
    }

    @Override
    public void delete(Long[] ids) {
        boolean flag = baseService.deleteBatch(ids);
        ConditionCheck.falseAndThrow(flag, new CustomException("删除角色失败"));
    }

    @Override
    public void update(RoleBO roleBO) {

    }
}
