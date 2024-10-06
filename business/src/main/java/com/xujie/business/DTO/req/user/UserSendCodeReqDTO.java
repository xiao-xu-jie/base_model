package com.xujie.business.DTO.req.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户发送验证码请求DTO
 *
 * @author Xujie
 * @since 2024/10/5 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSendCodeReqDTO {
  @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
  private String phone;
}
