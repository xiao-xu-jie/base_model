package com.xujie.business.controller.user;

import com.xujie.business.dto.user.UserDto;
import com.xujie.business.service.common.CommonService;
import com.xujie.business.service.user.interfa.IUserService;
import com.xujie.future.contract.response.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xujie
 * @since 2025/7/1 13:07
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/dj/user")
public class UserController {
    private final IUserService userService;
    private final CommonService commonService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserDto.UserRegisterRequest userRegisterRequest) {
        // 校验验证码
        Boolean flag = commonService.verifyCode(userRegisterRequest.getPhoneNumber(), userRegisterRequest.getPhoneCode());
        if (!flag) {
            return ResponseEntity.fail("验证码错误");
        }
        // 注册用户
        userService.registerUser(userRegisterRequest);
        return ResponseEntity.success("用户注册成功");
    }
}
