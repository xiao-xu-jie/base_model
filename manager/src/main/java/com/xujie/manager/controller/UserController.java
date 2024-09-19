package com.xujie.manager.controller;

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

    @GetMapping("/selectPage")
    public Result<Page<UserQueryResDTO>> selectPage(@ModelAttribute("user") UserQueryReqDTO userQueryReqDTO,
                                                    @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                                    @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        Page<UserBO> userPageList = userDomainService.getUserPageList(userConvert.convertQueryReqDTO2BO(userQueryReqDTO), pageNum, pageSize);
        return Result.ok(userConvert.convertPageBO2DTO(userPageList));
    }

    @PostMapping("/add")
    public Result<Object> addUser(@RequestBody @Validated UserAddReqDTO userAddReqDTO) {

        userDomainService.addUser(userConvert.convertAddReqDTO2BO(userAddReqDTO));
        return Result.okMessage("添加成功");
    }

    @DeleteMapping("/delete")
    public Result<Object> deleteUser(@RequestParam(name = "id", required = true) Long id) {
        userDomainService.deleteUser(id);
        return Result.okMessage("删除成功");
    }

    @PutMapping("/update")
    public Result<Object> updateUser(@RequestBody @Validated UserAddReqDTO userAddReqDTO) {
        userDomainService.updateUser(userConvert.convertAddReqDTO2BO(userAddReqDTO));
        return Result.okMessage("修改成功");
    }
}
