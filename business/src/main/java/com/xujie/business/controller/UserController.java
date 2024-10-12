package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.xujie.business.DTO.req.user.*;
import com.xujie.business.DTO.res.user.UserLoginResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.common.utils.SMSUtil;
import com.xujie.business.convert.UserConvert;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.domain.service.UserDomainService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author Xujie
 * @since 2024/10/5 14:52
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Resource private UserDomainService userDomainService;
  @Resource private UserConvert userConvert;
  @Resource private SMSUtil smsUtil;

  /**
   * 通过手机号登录
   *
   * @param userPhoneLonginReqDTO 手机号登录请求
   * @return 用户信息
   */
  @PostMapping("/loginByPhone")
  Result<UserLoginResDTO> loginByPhone(
      @RequestBody @Validated UserPhoneLonginReqDTO userPhoneLonginReqDTO) {
    BizUserBO bizUserBO =
        userDomainService.loginByPhone(
            userPhoneLonginReqDTO.getPhone(), userPhoneLonginReqDTO.getCode());
    UserLoginResDTO userLoginResDTO = userConvert.convertBO2LoginResDTO(bizUserBO);
    userLoginResDTO.setToken(StpUtil.getTokenValue());
    return Result.ok(userLoginResDTO);
  }

  /**
   * 发送验证码
   *
   * @param userSendCodeReqDTO 手机号登录请求
   * @return 是否发送成功
   */
  @PostMapping("/sendCode")
  Result<Boolean> sendCode(@RequestBody @Validated UserSendCodeReqDTO userSendCodeReqDTO) {
    return Result.ok(userDomainService.sendCode(userSendCodeReqDTO.getPhone()));
  }

  /**
   * 通过微信登录
   *
   * @param wxLoginReqDTO 微信登录请求
   * @return 用户信息
   */
  @SaIgnore
  @PostMapping("/loginByWx")
  Result<UserLoginResDTO> loginByWx(@RequestBody @Validated UserWxLoginReqDTO wxLoginReqDTO) {
    BizUserBO bizUserBO = userDomainService.loginByWx(wxLoginReqDTO.getCode());
    UserLoginResDTO userLoginResDTO = userConvert.convertBO2LoginResDTO(bizUserBO);
    userLoginResDTO.setToken(StpUtil.getTokenValue());
    return Result.ok(userLoginResDTO);
  }

  /**
   * 用户注册
   *
   * @param userRegisterReqDTO 用户注册请求
   * @return
   */
  @PostMapping("/register")
  public Result<UserLoginResDTO> register(
      @RequestBody @Validated UserRegisterReqDTO userRegisterReqDTO) {
    BizUserBO userBO = userConvert.convertRegisterReqDTO2BO(userRegisterReqDTO);
    smsUtil.checkCode(userBO.getPhone(), userRegisterReqDTO.getPhoneCode());
    BizUserBO bizUserBO = userDomainService.register(userBO, userRegisterReqDTO.getCode());
    UserLoginResDTO userLoginResDTO = userConvert.convertBO2LoginResDTO(bizUserBO);
    userLoginResDTO.setToken(StpUtil.getTokenValue());
    return Result.ok(userLoginResDTO);
  }

  /**
   * 获取用户信息
   *
   * @return 用户信息
   */
  @GetMapping("/profile")
  public Result<UserLoginResDTO> profile() {
    BizUserBO userProfile = userDomainService.getUserProfile(StpUtil.getLoginIdAsLong());
    UserLoginResDTO userLoginResDTO = userConvert.convertBO2LoginResDTO(userProfile);
    userLoginResDTO.setToken(StpUtil.getTokenValue());
    return Result.ok(userLoginResDTO);
  }

  /**
   * 更新用户信息
   *
   * @param userUpdateReqDTO 用户更新请求
   * @return 是否更新成功
   */
  @PostMapping("/update")
  public Result<Boolean> updateProfile(@RequestBody @Validated UserUpdateReqDTO userUpdateReqDTO) {
    userDomainService.updateUserInfo(
        StpUtil.getLoginIdAsLong(), userConvert.convertUpdateReqDTO2BO(userUpdateReqDTO));
    return Result.ok(Boolean.TRUE);
  }
}
