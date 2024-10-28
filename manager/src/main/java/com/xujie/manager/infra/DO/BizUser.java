package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.manager.common.base.model.BaseDO;
import java.util.Date;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_user")
public class BizUser extends BaseDO {
  public static final String COL_id = "id";
  public static final String COL_phone = "phone";
  public static final String COL_wxOpenId = "wx_open_id";
  public static final String COL_wxUnionId = "wx_union_id";
  public static final String COL_nickName = "nick_name";
  public static final String COL_userAvatar = "user_avatar";
  public static final String COL_userDesc = "user_desc";
  public static final String COL_userLocation = "user_location";
  public static final String COL_userStatus = "user_status";
  public static final String COL_createTime = "create_time";
  public static final String COL_updateTime = "update_time";
  public static final String COL_isDelete = "is_delete";

  /** 用户ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 用户手机号 */
  @TableField(value = "phone")
  private String phone;

  /** 微信openid */
  @TableField(value = "wx_open_id")
  private String wxOpenId;

  /** 微信公共id */
  @TableField(value = "wx_union_id")
  private String wxUnionId;

  /** 用户名 */
  @TableField(value = "nick_name")
  private String nickName;

  /** 头像 */
  @TableField(value = "user_avatar")
  private String userAvatar;

  /** 用户介绍 */
  @TableField(value = "user_desc")
  private String userDesc;

  /** 用户位置 */
  @TableField(value = "user_location")
  private String userLocation;

  /** 用户状态-0,1 */
  @TableField(value = "user_status")
  private Integer userStatus;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  @TableLogic
  @TableField(value = "is_delete")
  private Integer isDelete;

  public static final String COL_ID = "id";

  public static final String COL_PHONE = "phone";

  public static final String COL_WX_OPEN_ID = "wx_open_id";

  public static final String COL_WX_UNION_ID = "wx_union_id";

  public static final String COL_NICK_NAME = "nick_name";

  public static final String COL_USER_AVATAR = "user_avatar";

  public static final String COL_USER_DESC = "user_desc";

  public static final String COL_USER_LOCATION = "user_location";

  public static final String COL_USER_STATUS = "user_status";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
