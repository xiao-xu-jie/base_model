package com.xujie.manager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.xujie.manager.DTO.req.LoginReqDTO;
import com.xujie.manager.DTO.res.RouterResDTO;
import com.xujie.manager.DTO.res.UserLoginResDTO;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.RouterBO;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.domain.convert.RouterConvert;
import com.xujie.manager.domain.service.RouterDomainService;
import com.xujie.manager.domain.service.UserDomainService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用控制器
 *
 * @author Xujie
 * @since 2024/9/17 20:56
 **/

@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private RouterDomainService routerDomainService;

    @Resource
    private RouterConvert dtoConvert;

    @Resource
    private UserDomainService userDomainService;

    @PostMapping("/login")
    public Result<UserLoginResDTO> userLogin(@RequestBody @Validated LoginReqDTO loginReqDTO) {
        String password = loginReqDTO.getPassword();
        String username = loginReqDTO.getUsername();
        UserBO userBO = userDomainService.handleLogin(username, password);
        UserLoginResDTO user = dtoConvert.convertBo2LoginDTO(userBO);
        user.setAccessToken(StpUtil.getTokenValue());
        user.setRefreshToken(StpUtil.getTokenValue());
        user.setRoles(StpUtil.getRoleList());
        user.setPermissions(StpUtil.getPermissionList());
        return Result.ok(user);
    }

    @GetMapping("/getSyncRouter")
    public Result<List<RouterResDTO>> getSyncRouter() {
        List<RouterBO> routers = routerDomainService.getRouters();
        List<RouterResDTO> routerResDTOS = dtoConvert.convertBO2DO(routers);
        return Result.ok(routerResDTOS);
    }
}
