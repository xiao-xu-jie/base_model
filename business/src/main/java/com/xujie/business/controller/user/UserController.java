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
 * 用户控制器
 *
 * @author Xujie
 * @since 2025/7/1 13:07
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/dj/user")
public class UserController {
    private final IUserService userService;
    private final CommonService commonService;

    /**
     * 用户注册接口
     *
     * @param userRegisterRequest 用户注册请求参数
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserDto.UserRegisterRequest userRegisterRequest) {
        // 校验验证码
        Boolean flag = commonService.verifyCode(userRegisterRequest.getPhoneNumber(), userRegisterRequest.getPhoneCode());
        if (!flag) {
            return ResponseEntity.fail("验证码错误或者已经过期，请重新获取验证码");
        }
        // 注册用户
        userService.registerUser(userRegisterRequest);
        return ResponseEntity.success("用户注册成功");
    }


    /**
     * 用户登录接口
     *
     * @param userLoginRequest 用户登录请求参数
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto.UserLoginResponse> userLogin(@RequestBody UserDto.UserLoginRequest userLoginRequest) {
        return ResponseEntity.success(userService.loginUser(userLoginRequest));
    }
}
