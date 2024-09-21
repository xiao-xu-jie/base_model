package com.xujie.manager.domain.service;


import com.xujie.manager.common.base.service.BaseDomainService;
import com.xujie.manager.domain.BO.UserBO;

import java.util.List;

/**
 * 用户领域服务
 */

public interface UserDomainService extends BaseDomainService<UserBO> {

    UserBO handleLogin(String username, String password);

    List<String> getUserRoleList(Long userId);
}
