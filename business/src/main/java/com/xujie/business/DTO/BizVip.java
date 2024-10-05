package com.xujie.business.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizVip {
  /** 会员ID */
  @NotNull(message = "会员ID不能为null")
  private Long id;

  /** 会员名称 */
  @Size(max = 255, message = "会员名称最大长度要小于 255")
  @NotBlank(message = "会员名称不能为空")
  private String vipName;

  /** 会员描述 */
  @Size(max = 255, message = "会员描述最大长度要小于 255")
  private String vipDesc;

  /** 会员图标 */
  @Size(max = 255, message = "会员图标最大长度要小于 255")
  private String vipIcon;

  /** 会员图片 */
  @Size(max = 255, message = "会员图片最大长度要小于 255")
  private String vipImg;

  /** 会员价格 */
  @NotNull(message = "会员价格不能为null")
  private BigDecimal vipPrice;

  /** 会员发布次数 */
  @NotNull(message = "会员发布次数不能为null")
  private Integer vipPostCount;

  /** 订阅通知次数 */
  @NotNull(message = "订阅通知次数不能为null")
  private Integer vipNotifyCount;

  /** 会员有效时间 */
  @NotNull(message = "会员有效时间不能为null")
  private Integer vipEffectiveDay;

  /** 会员状态 0,1 */
  @NotNull(message = "会员状态 0,1不能为null")
  private Integer vipStatus;

  @NotNull(message = "不能为null")
  private Date createTime;

  @NotNull(message = "不能为null")
  private Date updateTime;

  @Size(max = 255, message = "最大长度要小于 255")
  @NotBlank(message = "不能为空")
  private String isDelete;
}
