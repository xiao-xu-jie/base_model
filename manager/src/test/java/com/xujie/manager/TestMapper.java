package com.xujie.manager;

import com.xujie.ManagerApplication;
import com.xujie.manager.infra.mapper.SysRoutersMapper;
import com.xujie.manager.infra.service.RoleService;
import com.xujie.manager.infra.service.UserRoleService;
import com.xujie.manager.infra.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/9/17 22:18
 **/

@SpringBootTest(classes = ManagerApplication.class)
public class TestMapper {

    @Resource
    private SysRoutersMapper sysRoutersMapper;

    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;


    @Test
    public void test() {
//        List<String> roleList = userRoleService.getRoleList(1836671538925948929L);
//        System.out.println(roleList);
    }
}
