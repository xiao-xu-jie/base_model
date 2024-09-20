package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.SysUser;
import com.xujie.manager.infra.mapper.SysUserMapper;
import com.xujie.manager.infra.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author Xujie
 * @since 2024/9/18 15:21
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private SysUserMapper baseMapper;

    @Override
    public SysUser getOneUserByUserEntity(SysUser user) {
        return getAllUserByUserEntity(user).stream().findFirst().orElse(null);
    }

    @Override
    public List<SysUser> getAllUserByUserEntity(SysUser user) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .eq(ObjectUtils.isNotNull(user.getId()), SysUser::getId, user.getId())
                .eq(StringUtils.isNotBlank(user.getUsername()), SysUser::getUsername, user.getUsername())
                .eq(StringUtils.isNotBlank(user.getPassword()), SysUser::getPassword, user.getPassword())
                .eq(StringUtils.isNotBlank(user.getEmail()), SysUser::getEmail, user.getEmail())
                .eq(StringUtils.isNotBlank(user.getPhone()), SysUser::getPhone, user.getPhone());
        List<SysUser> sysUserList = baseMapper.selectList(queryWrapper);
        return sysUserList;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public boolean addUser(SysUser user) {
        try {
            baseMapper.insert(user);
        } catch (Exception e) {
            log.error("添加用户失败", e);
            return false;
        }
        return true;
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public boolean deleteUser(Long id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return false;
        }
        return true;
    }

    /**
     * 更新用户
     *
     * @param id
     * @param user
     */
    @Override
    public boolean updateUser(Long id, SysUser user) {
        return baseMapper.updateById(user, id) > 0;
    }

    @Override
    public Page<SysUser> getUserPageList(SysUser user, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .eq(ObjectUtils.isNotNull(user.getId()), SysUser::getId, user.getId())
                .eq(StringUtils.isNotBlank(user.getUsername()), SysUser::getUsername, user.getUsername())
                .eq(StringUtils.isNotBlank(user.getPassword()), SysUser::getPassword, user.getPassword())
                .eq(StringUtils.isNotBlank(user.getEmail()), SysUser::getEmail, user.getEmail())
                .eq(StringUtils.isNotBlank(user.getPhone()), SysUser::getPhone, user.getPhone());
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);

    }
}
