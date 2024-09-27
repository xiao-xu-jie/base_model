package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.manager.common.base.model.BaseDO;
import java.util.Date;
import lombok.*;

/**
 * (BizCategory)表实体类
 *
 * @author xujie
 * @since 2024-09-27 19:02:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "biz_category")
public class BizCategory extends BaseDO {

  public static final String COL_id = "id";
  public static final String COL_categoryName = "category_name";
  public static final String COL_categoryRank = "category_rank";
  public static final String COL_categoryDesc = "category_desc";
  public static final String COL_categoryStatus = "category_status";
  public static final String COL_createTime = "create_time";
  public static final String COL_updateTime = "update_time";
  public static final String COL_isDelete = "is_delete";

  /** 分类ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 分类名称 */
  @TableField(value = "category_name")
  private String categoryName;

  /** 分类排序 */
  @TableField(value = "category_rank")
  private Integer categoryRank;

  /** 分类描述 */
  @TableField(value = "category_desc")
  private String categoryDesc;

  /** 分类状态 */
  @TableField(value = "category_status")
  private Integer categoryStatus;

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
