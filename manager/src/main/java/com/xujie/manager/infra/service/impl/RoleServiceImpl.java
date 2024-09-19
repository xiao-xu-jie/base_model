package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.mapper.SysRoleMapper;
import com.xujie.manager.infra.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务实现类
 *
 * @author Xujie
 * @since 2024/9/18 15:31
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private SysRoleMapper baseMapper;

    @Override
    public boolean addRole(SysRole role) {
        try {
            baseMapper.insert(role);
        } catch (Exception e) {
            log.error("添加角色失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean deleteRole(Long id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("删除角色失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean updateRole(Long id, SysRole role) {
        try {
            role.setId(id);
            baseMapper.updateById(role);
        } catch (Exception e) {
            log.error("更新角色失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<SysRole> getAllRoleByEntity(SysRole role) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ObjectUtils.isNotNull(role.getId()), SysRole::getId, role.getId())
                .eq(ObjectUtils.isNotNull(role.getName()), SysRole::getName, role.getName())
                .eq(ObjectUtils.isNotNull(role.getCode()), SysRole::getCode, role.getCode())
                .like(ObjectUtils.isNotNull(role.getRoleDesc()), SysRole::getRoleDesc, role.getRoleDesc());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public SysRole getOneRoleByEntity(SysRole role) {
        return getAllRoleByEntity(role).stream().findFirst().orElse(null);
    }
}
