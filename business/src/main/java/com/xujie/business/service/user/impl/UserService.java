package com.xujie.business.service.user.impl;

import com.xujie.business.application.sms.service.impl.SmsService;
import com.xujie.business.dto.user.UserDto;
import com.xujie.business.entity.user.User;
import com.xujie.business.mapper.user.UserMapper;
import com.xujie.business.service.common.CommonService;
import com.xujie.business.service.user.interfa.IUserService;
import com.xujie.future.contract.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Xujie
 * @since 2025/7/1 13:17
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserMapper userMapper;
    private final SmsService smsService;
    private final CommonService commonService;

    @Override
    public void registerUser(UserDto.UserRegisterRequest userRegisterRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterRequest, user);
        // 检验用户名、手机号是否已存在
        if (userMapper.existsByUsername(user.getUsername())) {
            log.error("注册失败：用户名已存在");
            throw new IllegalArgumentException("用户名已存在");
        }
        if (userMapper.existsByPhone(user.getPhoneNumber())) {
            log.error("注册失败：手机号已存在");
            throw new IllegalArgumentException("手机号已存在");
        }
        // 保存信息
        userMapper.save(user);
    }

    @Override
    public UserDto.UserLoginResponse loginUser(UserDto.UserLoginRequest userLoginRequest) {
        // mode1: 通过用户名和密码登录
        if (userLoginRequest.getUsername() != null && userLoginRequest.getPassword() != null) {
            User user = userMapper.findByUsernameAndPassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
            if (user != null) {
                UserDto.UserLoginResponse response = new UserDto.UserLoginResponse();
                BeanUtils.copyProperties(user, response);
                return response;
            } else {
                log.error("登录失败：用户名或密码错误");
                throw new BusinessException("用户名或密码错误");
            }
        }

        // mode2: 通过手机号和验证码登录
        if (userLoginRequest.getPhoneNumber() != null && userLoginRequest.getPhoneCode() != null) {
            User user = userMapper.findByPhone(userLoginRequest.getPhoneNumber());
            if (user != null) {
                // 校验验证码
                Boolean verified = commonService.verifyCode(userLoginRequest.getPhoneNumber(), userLoginRequest.getPhoneCode());
                if (!verified) {
                    log.error("登录失败：验证码错误");
                    throw new BusinessException("验证码错误或者已过期，请重新获取");
                }
                UserDto.UserLoginResponse response = new UserDto.UserLoginResponse();
                BeanUtils.copyProperties(user, response);
                return response;
            } else {
                log.error("登录失败：手机号或验证码错误");
                throw new BusinessException("用户信息错误或者用户被禁用");
            }
        }

        log.error("登录失败：未提供有效的登录信息");
        throw new BusinessException("未提供有效的登录信息");
    }
}
