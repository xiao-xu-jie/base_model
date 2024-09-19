package com.xujie.manager.infra.service.impl;

import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.DO.SysUserRole;
import com.xujie.manager.infra.mapper.SysUserRoleMapper;
import com.xujie.manager.infra.service.UserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户角色服务实现类
 *
 * @author Xujie
 * @since 2024/9/18 19:51
 **/

@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private SysUserRoleMapper baseMapper;

    @Override
    public boolean addUserRole(Long userId, Long roleId) {
        try {
            baseMapper.insert(SysUserRole.builder().roleId(roleId).userId(userId).build());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserRole(Long userId, Long roleId) {
        try {
            baseMapper.deleteByMap(Map.of("user_id", userId, "role_id", roleId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> getRoleList(Long loginId) {
        List<SysRole> roleList = baseMapper.selectRoleByUserId(loginId);
        List<String> list = roleList.stream().map(SysRole::getCode).toList();
        return list;
    }
}
