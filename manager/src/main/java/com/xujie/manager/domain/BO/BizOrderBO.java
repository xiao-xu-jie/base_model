package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizOrder)BO
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizOrderBO extends BaseBO {

  /** 订单ID */
  private Long id;

  /** 订单号 */
  private Long orderNo;

  /** 商品ID */
  private Long goodId;

  /** 商品名称 */
  private String goodName;

  /** 商品描述 */
  private String goodDesc;

  /** 商品价格 */
  private BigDecimal totalPrice;

  /** 学校 */
  private String school;

  /** 手机号 */
  private String phone;

  /** 密码 */
  private String password;

  /** 课程ID 数组 */
  private Object classInfo;

  /** 订单状态 */
  private Integer orderStatus;

  /** 创建时间 */
  private Date createTime;

  /** 支付时间 */
  private Date payTime;

  /** 是否删除 */
  private Integer isDelete;

  /** 版本号 */
  private Long version;
}
