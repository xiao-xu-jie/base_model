package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName(value = "biz_vip")
public class BizVip {
  /** 会员ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 会员名称 */
  @TableField(value = "vip_name")
  private String vipName;

  /** 会员描述 */
  @TableField(value = "vip_desc")
  private String vipDesc;

  /** 会员图标 */
  @TableField(value = "vip_icon")
  private String vipIcon;

  /** 会员图片 */
  @TableField(value = "vip_img")
  private String vipImg;

  /** 会员价格 */
  @TableField(value = "vip_price")
  private BigDecimal vipPrice;

  /** 会员发布次数 */
  @TableField(value = "vip_post_count")
  private Integer vipPostCount;

  /** 订阅通知次数 */
  @TableField(value = "vip_notify_count")
  private Integer vipNotifyCount;

  /** 会员有效时间 */
  @TableField(value = "vip_effective_day")
  private Integer vipEffectiveDay;

  /** 会员状态 0,1 */
  @TableField(value = "vip_status")
  private Integer vipStatus;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  @TableField(value = "is_delete")
  @TableLogic
  private String isDelete;

  public static final String COL_ID = "id";

  public static final String COL_VIP_NAME = "vip_name";

  public static final String COL_VIP_DESC = "vip_desc";

  public static final String COL_VIP_ICON = "vip_icon";

  public static final String COL_VIP_IMG = "vip_img";

  public static final String COL_VIP_PRICE = "vip_price";

  public static final String COL_VIP_POST_COUNT = "vip_post_count";

  public static final String COL_VIP_NOTIFY_COUNT = "vip_notify_count";

  public static final String COL_VIP_EFFECTIVE_DAY = "vip_effective_day";

  public static final String COL_VIP_STATUS = "vip_status";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
