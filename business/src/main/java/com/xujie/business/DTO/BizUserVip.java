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
public class BizUserVip {
  /** 用户id */
  @NotNull(message = "用户id不能为null")
  private Long userId;

  /** 会员ID */
  @NotNull(message = "会员ID不能为null")
  private Long vipId;

  /** 会员名称 */
  @Size(max = 255, message = "会员名称最大长度要小于 255")
  @NotBlank(message = "会员名称不能为空")
  private String vipName;

  /** 到期时间 */
  @NotNull(message = "到期时间不能为null")
  private Date expireTime;

  /** 是否到期 */
  @NotNull(message = "是否到期不能为null")
  private Integer isExpire;

  /** 发布次数 */
  @NotNull(message = "发布次数不能为null")
  private Integer postCount;

  /** 通知次数 */
  @NotNull(message = "通知次数不能为null")
  private Integer notifyCount;
}
