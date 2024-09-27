package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.manager.common.base.model.BaseDO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 * (BizGood)表实体类
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "biz_good")
public class BizGood extends BaseDO {

  public static final String COL_id = "biz_good.id";
  public static final String COL_categoryId = "biz_good.category_id";
  public static final String COL_goodName = "biz_good.good_name";
  public static final String COL_goodDesc = "biz_good.good_desc";
  public static final String COL_goodPrice = "biz_good.good_price";
  public static final String COL_goodRank = "biz_good.good_rank";
  public static final String COL_goodParams = "biz_good.good_params";
  public static final String COL_platformId = "biz_good.platform_id";
  public static final String COL_stationId = "biz_good.station_id";
  public static final String COL_createTime = "biz_good.create_time";
  public static final String COL_updateTime = "biz_good.update_time";
  public static final String COL_isDelete = "biz_good.is_delete";

  /** 商品ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 分类名称 */
  @TableField(value = "category_name")
  private String categoryName;

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
  private Object goodParams;

  /** 平台ID */
  @TableField(value = "platform_id")
  private Integer platformId;

  /** 货源名称 */
  @TableField(value = "station_name")
  private String stationName;

  /** 货源站ID */
  @TableField(value = "station_id")
  private Long stationId;

  /** 创建时间 */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  /** 更新时间 */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  /** 是否删除 */
  @TableField(value = "is_delete")
  @TableLogic(value = "0", delval = "1")
  private Integer isDelete;
}
