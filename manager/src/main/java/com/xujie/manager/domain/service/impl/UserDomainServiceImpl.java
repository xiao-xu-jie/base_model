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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void add(UserBO userBO) {
        Long userId = baseService.addOne(userConvert.convertBO2DO(userBO));
        userRoleService.saveUserRole(userId, userBO.getRoles());
        ConditionCheck.nullAndThrow(userId, new CustomException("添加用户失败"));
    }

    @Override
    public Page<UserBO> getPageList(UserBO userBO, Integer pageNum, Integer pageSize) {
        Page<SysUser> page = baseService.getPageList(userConvert.convertBO2DO(userBO), pageNum, pageSize);
        return userConvert.convertPageDO2BO(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        boolean b = baseService.deleteBatch(ids);
        ConditionCheck.falseAndThrow(b, new CustomException("删除用户失败"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserBO userBO) {
        boolean b = baseService.updateOne(userBO.getId(), userConvert.convertBO2DO(userBO));
        userRoleService.saveUserRole(userBO.getId(), userBO.getRoles());
        ConditionCheck.falseAndThrow(b, new CustomException("更新用户失败"));
    }

    @Override
    public UserBO handleLogin(String username, String password) {
        SysUser user = baseService.getOneByEntity(SysUser.builder().username(username).password(password).build());
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
