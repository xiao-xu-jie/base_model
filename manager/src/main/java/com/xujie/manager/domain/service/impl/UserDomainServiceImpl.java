package com.xujie.manager.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.domain.convert.UserConvert;
import com.xujie.manager.domain.service.UserDomainService;
import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.DO.SysUser;
import com.xujie.manager.infra.service.UserRoleService;
import com.xujie.manager.infra.service.UserService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/19 10:46
 **/

@Slf4j
@Service
public class UserDomainServiceImpl implements UserDomainService {
    @Resource
    private UserService baseService;

    @Resource
    private UserConvert userConvert;

    @Resource
    private UserRoleService userRoleService;

    /**
     * 添加用户
     *
     * @param userBO 用户信息
     */
    @Override
    public void addUser(UserBO userBO) {
        boolean success = baseService.addUser(userConvert.convertBO2DO(userBO));
        userRoleService.saveUserRole(userBO.getId(), userBO.getRoles());
        ConditionCheck.falseAndThrow(success, new CustomException("添加用户失败"));
    }

    @Override
    public Page<UserBO> getUserPageList(UserBO userBO, Integer pageNum, Integer pageSize) {
        Page<SysUser> page = baseService.getUserPageList(userConvert.convertBO2DO(userBO), pageNum, pageSize);
        return userConvert.convertPageDO2BO(page);
    }

    @Override
    public void deleteUser(Long id) {
        boolean b = baseService.deleteUser(id);
        ConditionCheck.falseAndThrow(b, new CustomException("删除用户失败"));
    }

    @Override
    public void updateUser(UserBO userBO) {
        boolean b = baseService.updateUser(userBO.getId(), userConvert.convertBO2DO(userBO));
        userRoleService.saveUserRole(userBO.getId(), userBO.getRoles());
        ConditionCheck.falseAndThrow(b, new CustomException("更新用户失败"));
    }

    @Override
    public UserBO handleLogin(String username, String password) {
        SysUser user = baseService.getOneUserByUserEntity(SysUser.builder().username(username).password(password).build());
        ConditionCheck.anyNull(new CustomException("用户名或密码错误"), user);
        StpUtil.login(user.getId());
        UserBO userBO = userConvert.convertDO2BO(user);
        return userBO;
    }

    @Override
    public List<String> getUserRoleList(Long userId) {
        List<SysRole> roleList = userRoleService.getRoleListByUserId(userId);
        return roleList.stream().map(SysRole::getCode).toList();
    }
}
