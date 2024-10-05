package com.xujie.business.DTO.req.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户微信登录请求DTO
 *
 * @author Xujie
 * @since 2024/10/5 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWxLoginReqDTO {
  /** 微信code */
  private String code;
}
