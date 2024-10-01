package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xujie.business.common.enums.GoodStatusEnum;
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
@TableName(value = "biz_good")
public class BizGood {
  /** 商品ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 分类id */
  @TableField(value = "category_id")
  private Long categoryId;

  /** 商品名称 */
  @TableField(value = "good_name")
  private String goodName;

  /** 商品描述 */
  @TableField(value = "good_desc")
  private String goodDesc;

  /** 商品价格 */
  @TableField(value = "good_price")
  private BigDecimal goodPrice;

  /** 商品排序 */
  @TableField(value = "good_rank")
  private Integer goodRank;

  /** 商品参数 */
  @TableField(value = "good_params")
  private String goodParams;

  /** 平台ID */
  @TableField(value = "platform_id")
  private Integer platformId;

  /** 货源站ID */
  @TableField(value = "station_id")
  private Long stationId;

  /** 商品状态 */
  @TableField(value = "good_status")
  private GoodStatusEnum goodStatus;

  /** 创建时间 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  /** 更新时间 */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  /** 是否删除 */
  @TableField(value = "is_delete")
  private Integer isDelete;

  public static final String COL_ID = "id";

  public static final String COL_CATEGORY_ID = "category_id";

  public static final String COL_GOOD_NAME = "good_name";

  public static final String COL_GOOD_DESC = "good_desc";

  public static final String COL_GOOD_PRICE = "good_price";

  public static final String COL_GOOD_RANK = "good_rank";

  public static final String COL_GOOD_PARAMS = "good_params";

  public static final String COL_STATION_ID = "station_id";

  public static final String COL_GOOD_STATUS = "good_status";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
