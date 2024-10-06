package com.xujie.business.DTO.res.vip;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员查询响应DTO
 *
 * @author Xujie
 * @since 2024/10/6 11:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipQueryResDTO {
  /** 会员ID */
  private Long id;

  /** 会员名称 */
  private String vipName;

  /** 会员描述 */
  private String vipDesc;

  /** 会员图标 */
  private String vipIcon;

  /** 会员图片 */
  private String vipImg;

  /** 会员价格 */
  private BigDecimal vipPrice;

  /** 会员发布次数 */
  private Integer vipPostCount;

  /** 订阅通知次数 */
  private Integer vipNotifyCount;

  /** 会员有效时间 */
  private Integer vipEffectiveDay;
}
