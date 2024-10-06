package com.xujie.business.DTO.res.vip;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员用户响应DTO
 *
 * @author Xujie
 * @since 2024/10/6 11:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipUserResDTO {
  /** 会员ID */
  private Long vipId;

  /** 会员名称 */
  private String vipName;

  /** 到期时间 */
  private Date expireTime;

  /** 是否到期 */
  private Integer isExpire;

  /** 会员发布次数 */
  private Integer vipPostCount;

  /** 订阅通知次数 */
  private Integer vipNotifyCount;

  /** 会员图标 */
  private String vipIcon;

  /** 会员图片 */
  private String vipImg;
}
