package com.xujie.business.domain.BO;

import com.xujie.business.common.enums.ShowStatusEnum;
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
public class BizVipBO {
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

  /** 会员状态 0,1 */
  private ShowStatusEnum vipStatus;

  /** 到期时间 */
  private Date expireTime;
}
