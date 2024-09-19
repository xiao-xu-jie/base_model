package com.xujie.manager.infra.service;

import java.util.List;

public interface UserRoleService {
    boolean addUserRole(Long userId, Long roleId);

    boolean deleteUserRole(Long userId, Long roleId);

    List<String> getRoleList(Long loginId);
}
