package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.business.common.enums.ShowStatusEnum;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 发布页面轮播 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_slider")
public class BizSlider {
  /** 幻灯片ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 内容 */
  @TableField(value = "content")
  private String content;

  /** 图片内容 */
  @TableField(value = "img_url")
  private String imgUrl;

  /** 是否显示 */
  @TableField(value = "show_status")
  private ShowStatusEnum showStatus;

  @TableField(value = "params")
  private String params;

  /** 排序 */
  @TableField(value = "`rank`")
  private Integer rank;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  @TableLogic
  @TableField(value = "is_delete")
  private Integer isDelete;

  public static final String COL_ID = "id";

  public static final String COL_CONTENT = "content";

  public static final String COL_IMG_URL = "img_url";

  public static final String COL_SHOW_STATUS = "show_status";

  public static final String COL_PARAMS = "params";

  public static final String COL_RANK = "rank";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
