package com.xujie.business.service.user.impl;

import com.xujie.business.dto.user.UserDto;
import com.xujie.business.entity.user.User;
import com.xujie.business.mapper.user.UserMapper;
import com.xujie.business.service.user.interfa.IUserService;
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
}
