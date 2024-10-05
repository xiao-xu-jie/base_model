package com.xujie.business.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizUser {
  /** 用户ID */
  @NotNull(message = "用户ID不能为null")
  private Long id;

  /** 用户手机号 */
  @Size(max = 255, message = "用户手机号最大长度要小于 255")
  private String phone;

  /** 微信openid */
  @Size(max = 255, message = "微信openid最大长度要小于 255")
  private String wxOpenId;

  /** 微信公共id */
  @Size(max = 255, message = "微信公共id最大长度要小于 255")
  private String wxUnionId;

  /** 用户名 */
  @Size(max = 255, message = "用户名最大长度要小于 255")
  @NotBlank(message = "用户名不能为空")
  private String nickName;

  /** 头像 */
  @Size(max = 255, message = "头像最大长度要小于 255")
  @NotBlank(message = "头像不能为空")
  private String userAvatar;

  /** 用户介绍 */
  @Size(max = 255, message = "用户介绍最大长度要小于 255")
  private String userDesc;

  /** 用户位置 */
  @Size(max = 255, message = "用户位置最大长度要小于 255")
  private String userLocation;

  /** 用户状态-0,1 */
  @NotNull(message = "用户状态-0,1不能为null")
  private Integer userStatus;

  @NotNull(message = "不能为null")
  private Date createTime;

  @NotNull(message = "不能为null")
  private Date updateTime;

  @NotNull(message = "不能为null")
  private Integer isDelete;
}
