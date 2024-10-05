package com.xujie.business.DTO.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户手机登录请求DTO
 *
 * @author Xujie
 * @since 2024/10/5 14:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPhoneLonginReqDTO {
  @NotBlank(message = "手机号不能为空")
  @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
  private String phone;

  @NotBlank(message = "验证码不能为空")
  @Pattern(regexp = "^\\d{4}$", message = "验证码格式不正确")
  private String code;
}
