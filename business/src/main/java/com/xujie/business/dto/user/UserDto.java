package com.xujie.business.dto.user;

import com.xujie.business.commom.enums.user.UserGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

import static com.xujie.business.commom.constants.user.UserConstant.USER_DEFAULT_AVATAR;
import static com.xujie.business.entity.user.User.createRandomNickName;

/**
 * @author Xujie
 * @since 2025/7/1 13:08
 **/


public class UserDto {

    @Data
    @ToString
    public static class UserRegisterRequest {

        /**
         * 用户名
         */
        @NotBlank(message = "用户名不能为空")
        private String username;

        /**
         * 昵称
         */
        private String nickname = createRandomNickName();

        /**
         * 用户简介
         */
        private String userInstruction = "无";

        /**
         * 用户地区
         */
        @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,10}$", message = "地区格式不正确")
        private String userArea = "";

        /**
         * 密码
         */
        @NotBlank(message = "密码不能为空")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,16}$", message = "密码必须包含字母和数字，长度为6-16位")
        private String password;

        /**
         * 头像地址
         */
        private String avatar = USER_DEFAULT_AVATAR;

        /**
         * 电话号码
         */
        @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
        @NotBlank(message = "验证码不能为空")
        private String phoneNumber;


        /**
         * 性别 0-男 1-女
         */
        private Integer sex = UserGender.MAN.getCode();

        @NotBlank(message = "验证码不能为空")
        private String phoneCode;
    }
}
