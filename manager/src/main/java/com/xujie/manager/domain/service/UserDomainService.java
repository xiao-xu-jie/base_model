package com.xujie.manager.domain.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.UserBO;

import java.util.List;

/**
 * 用户领域服务
 */

public interface UserDomainService {
    /**
     * 添加用户
     *
     * @param userBO 用户信息
     */
    void addUser(UserBO userBO);

    Page<UserBO> getUserPageList(UserBO userBO, Integer pageNum, Integer pageSize);

    void deleteUser(Long id);

    void updateUser(UserBO userBO);

    UserBO handleLogin(String username, String password);

    List<String> getUserRoleList(Long userId);
}
