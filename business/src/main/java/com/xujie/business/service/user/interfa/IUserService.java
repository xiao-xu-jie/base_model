package com.xujie.business.service.user.interfa;

import com.xujie.business.dto.user.UserDto;

public interface IUserService {

    void registerUser(UserDto.UserRegisterRequest userRegisterRequest);

    UserDto.UserLoginResponse loginUser(UserDto.UserLoginRequest userLoginRequest);
}
