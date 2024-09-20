package com.xujie.manager.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.DTO.req.UserAddReqDTO;
import com.xujie.manager.DTO.req.UserQueryReqDTO;
import com.xujie.manager.DTO.res.UserQueryResDTO;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.domain.convert.UserConvert;
import com.xujie.manager.domain.service.UserDomainService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author Xujie
 * @since 2024/9/19 10:39
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserDomainService userDomainService;

    @Resource
    private UserConvert userConvert;

    /**
     * 分页查询用户
     *
     * @param userQueryReqDTO 查询条件
     * @param pageNum         页码
     * @param pageSize        每页数量
     * @return 用户列表
     */
    @GetMapping("/selectPage")
    public Result<Page<UserQueryResDTO>> selectPage(@ModelAttribute("user") UserQueryReqDTO userQueryReqDTO,
                                                    @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                                    @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        Page<UserBO> userPageList = userDomainService.getUserPageList(userConvert.convertQueryReqDTO2BO(userQueryReqDTO), pageNum, pageSize);
        return Result.ok(userConvert.convertPageBO2DTO(userPageList));
    }

    /**
     * 添加用户
     *
     * @param userAddReqDTO 用户信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<Object> addUser(@RequestBody @Validated UserAddReqDTO userAddReqDTO) {

        userDomainService.addUser(userConvert.convertAddReqDTO2BO(userAddReqDTO));
        return Result.okMessage("添加成功");
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public Result<Object> deleteUser(@RequestParam(name = "id", required = true) Long id) {
        userDomainService.deleteUser(id);
        return Result.okMessage("删除成功");
    }

    /**
     * 修改用户
     *
     * @param userAddReqDTO 用户信息
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result<Object> updateUser(@RequestBody @Validated UserAddReqDTO userAddReqDTO) {
        userDomainService.updateUser(userConvert.convertAddReqDTO2BO(userAddReqDTO));
        return Result.okMessage("修改成功");
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    @GetMapping("/getUserRole")
    @SaCheckRole("admin")
    public Result<List<String>> getUserRole(@RequestParam(name = "id", required = true) Long id) {
        List<String> list = userDomainService.getUserRoleList(id);
        return Result.ok(list);
    }
}
