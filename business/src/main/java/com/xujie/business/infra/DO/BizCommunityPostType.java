package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_community_post_type")
public class BizCommunityPostType {
  /** 发布类型id */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 发布类型名称 */
  @TableField(value = "post_type_name")
  private String postTypeName;

  /** 发布类型描述 */
  @TableField(value = "post_type_desc")
  private String postTypeDesc;

  /** 发布类型图片 */
  @TableField(value = "post_type_img")
  private String postTypeImg;

  /** 发布类型状态 0,1 */
  @TableField(value = "post_type_status")
  private Integer postTypeStatus;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  @TableField(value = "is_delete")
  @TableLogic
  private Integer isDelete;

  public static final String COL_ID = "id";

  public static final String COL_POST_TYPE_NAME = "post_type_name";

  public static final String COL_POST_TYPE_DESC = "post_type_desc";

  public static final String COL_POST_TYPE_IMG = "post_type_img";

  public static final String COL_POST_TYPE_STATUS = "post_type_status";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
