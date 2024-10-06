package com.xujie.business.DTO.req.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册请求DTO
 *
 * @author Xujie
 * @since 2024/10/5 19:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterReqDTO {
  /** 用户手机号 */
  @NotBlank(message = "用户手机号不能为空")
  private String phone;

  /** 微信openid */
  @NotBlank(message = "微信code不能为空")
  private String code;

  /** 用户名 */
  @NotBlank(message = "用户名不能为空")
  private String nickName;

  /** 头像 */
  @NotBlank(message = "头像不能为空")
  private String userAvatar;

  /** 用户介绍 */
  @NotBlank(message = "用户介绍不能为空")
  private String userDesc;

  /** 用户位置 */
  @NotBlank(message = "用户位置不能为空")
  private String userLocation;
}
