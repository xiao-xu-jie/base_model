package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.business.common.enums.ShowStatusEnum;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_community_post")
public class BizCommunityPost {
  /** 发布ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 用户ID */
  @TableField(value = "user_id")
  private Long userId;

  /** 发布类型ID */
  @TableField(value = "post_type_id")
  private Long postTypeId;

  /** 发布类型名称 */
  @TableField(value = "post_type_name")
  private String postTypeName;

  /** 发布标题 */
  @TableField(value = "title")
  private String title;

  /** 内容 */
  @TableField(value = "content")
  private String content;

  /** 描述 */
  @TableField(value = "post_desc")
  private String postDesc;

  /** 封面 */
  @TableField(value = "cover_img")
  private String coverImg;

  /** 图片数组 */
  @TableField(value = "img_array")
  private String imgArray;

  /** 状态 0,1 */
  @TableField(value = "`status`")
  private ShowStatusEnum status;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  @TableField(value = "is_delete")
  @TableLogic
  private Integer isDelete;

  @TableField(exist = false)
  private String searchText;

  public static final String COL_ID = "id";

  public static final String COL_USER_ID = "user_id";

  public static final String COL_POST_TYPE_ID = "post_type_id";

  public static final String COL_POST_TYPE_NAME = "post_type_name";

  public static final String COL_TITLE = "title";

  public static final String COL_CONTENT = "content";

  public static final String COL_COVER_IMG = "cover_img";

  public static final String COL_IMG_ARRAY = "img_array";

  public static final String COL_STATUS = "status";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
