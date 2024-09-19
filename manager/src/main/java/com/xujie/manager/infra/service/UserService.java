package com.xujie.manager.infra.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.SysUser;

import java.util.List;

public interface UserService {
    SysUser getOneUserByUserEntity(SysUser user);

    List<SysUser> getAllUserByUserEntity(SysUser user);

    /**
     * 添加用户
     */
    boolean addUser(SysUser user);

    /**
     * 删除用户
     */
    boolean deleteUser(Long id);

    /**
     * 更新用户
     */
    boolean updateUser(Long id, SysUser user);

    Page<SysUser> getUserPageList(SysUser sysUser, Integer pageNum, Integer pageSize);
}
