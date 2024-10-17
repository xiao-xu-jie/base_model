package com.xujie.business.DTO.res.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户订阅列表响应DTO
 *
 * @author Xujie
 * @since 2024/10/17 16:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubResDTO {
  /** 用户ID */
  private Long id;

  /** 用户名 */
  private String nickName;

  /** 头像 */
  private String userAvatar;

  /** 用户介绍 */
  private String userDesc;

  /** 用户位置 */
  private String userLocation;

  private Integer status = 1;
}
