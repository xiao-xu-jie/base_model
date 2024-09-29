package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.manager.common.base.model.BaseDO;
import com.xujie.manager.common.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 * (BizOrder)表实体类
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "biz_order")
public class BizOrder extends BaseDO {

  public static final String COL_id = "id";
  public static final String COL_orderNo = "order_no";
  public static final String COL_goodId = "good_id";
  public static final String COL_goodName = "good_name";
  public static final String COL_goodDesc = "good_desc";
  public static final String COL_totalPrice = "total_price";
  public static final String COL_school = "school";
  public static final String COL_phone = "phone";
  public static final String COL_password = "password";
  public static final String COL_classInfo = "class_info";
  public static final String COL_orderStatus = "order_status";
  public static final String COL_createTime = "create_time";
  public static final String COL_payTime = "pay_time";
  public static final String COL_isDelete = "is_delete";
  public static final String COL_version = "version";

  /** 订单ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 订单号 */
  @TableField(value = "order_no")
  private Long orderNo;

  /** 商品ID */
  @TableField(value = "good_id")
  private Long goodId;

  /** 商品名称 */
  @TableField(value = "good_name")
  private String goodName;

  /** 商品描述 */
  @TableField(value = "good_desc")
  private String goodDesc;

  /** 商品价格 */
  @TableField(value = "total_price")
  private BigDecimal totalPrice;

  /** 学校 */
  @TableField(value = "school")
  private String school;

  /** 手机号 */
  @TableField(value = "phone")
  private String phone;

  /** 密码 */
  @TableField(value = "password")
  private String password;

  /** 课程ID 数组 */
  @TableField(value = "class_info")
  private Object classInfo;

  /** 订单状态 */
  @TableField(value = "order_status")
  private OrderStatusEnum orderStatus;

  /** 创建时间 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  /** 支付时间 */
  @TableField(value = "pay_time")
  private Date payTime;

  /** 是否删除 */
  @TableField(value = "is_delete")
  @TableLogic(value = "0", delval = "1")
  private Integer isDelete;

  /** 版本号 */
  @TableField(value = "version")
  private Long version;
}
