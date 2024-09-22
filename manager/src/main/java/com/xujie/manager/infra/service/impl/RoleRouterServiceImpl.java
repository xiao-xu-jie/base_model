package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.DO.SysRoleRouter;
import com.xujie.manager.infra.DO.SysRouters;
import com.xujie.manager.infra.mapper.SysRoleRouterMapper;
import com.xujie.manager.infra.mapper.SysRoutersMapper;
import com.xujie.manager.infra.service.RoleRouterService;
import com.xujie.manager.infra.service.RoleService;
import com.xujie.manager.infra.service.RouterService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/22 20:14
 **/

@Slf4j
@Service
public class RoleRouterServiceImpl implements RoleRouterService {
    @Resource
    private SysRoleRouterMapper baseMapper;
    @Resource
    private RouterService routerService;
    @Resource
    private RoleService roleService;

    @Override
    public void addRoleRouter(List<Long> routerIds, Long roleId) {
        List<SysRoleRouter> list = routerIds.stream()
                .map(id -> SysRoleRouter
                        .builder()
                        .routerId(id)
                        .roleId(roleId)
                        .build())
                .toList();
        List<BatchResult> insert = baseMapper.insert(list);
    }

    @Override
    public void deleteRoleRouterByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRoleRouter> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRoleRouter::getRoleId, roleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<SysRoleRouter> getRoleRouterByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRoleRouter> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysRoleRouter::getRoleId, roleId);
        List<SysRoleRouter> sysRoleRouters = baseMapper.selectList(wrapper);
        return removeAllTopRouterId(sysRoleRouters);
    }

    @Override
    public List<SysRoleRouter> getRoleRouterByRoleCode(List<String> roleList) {
        List<SysRole> allRoleByCodes = roleService.getAllRoleByCodes(roleList);
        List<Long> roleIds = allRoleByCodes.stream()
                .map(SysRole::getId)
                .toList();
        LambdaQueryWrapper<SysRoleRouter> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysRoleRouter::getRoleId, roleIds);
        return baseMapper.selectList(wrapper);
    }

    private List<SysRoleRouter> removeAllTopRouterId(List<SysRoleRouter> sysRoleRouters) {
        List<SysRouters> allTopRouters = routerService.getAllTopRouters();
        List<Long> ids = allTopRouters.stream().map(SysRouters::getId).toList();
        // 去掉所有顶级
        return sysRoleRouters.stream()
                .filter(item -> !ids.contains(item.getRouterId()))
                .toList();
    }
}
