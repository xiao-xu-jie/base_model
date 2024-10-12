package com.xujie.business.DTO.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户更新请求
 *
 * @author Xujie
 * @since 2024/10/12 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateReqDTO {
  /** 用户名 */
  @NotBlank(message = "用户名不能为空")
  private String nickName;

  /** 头像 */
  @NotBlank(message = "头像不能为空")
  @Pattern(regexp = "^(http|https)://.*$", message = "头像格式不正确")
  private String userAvatar;

  /** 用户介绍 */
  private String userDesc;

  /** 用户位置 */
  @NotBlank(message = "用户位置不能为空")
  private String userLocation;
}
