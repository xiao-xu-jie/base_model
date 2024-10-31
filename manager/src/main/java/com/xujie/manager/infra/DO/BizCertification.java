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
@TableName(value = "biz_certification")
public class BizCertification extends BaseDO {
  /** 认证ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 用户ID */
  @TableField(value = "user_id")
  private Long userId;

  @TableField(exist = false)
  private String nickName;

  /** 真实名称 */
  @TableField(value = "real_name")
  private String realName;

  /** 身份证号 */
  @TableField(value = "id_card_no")
  private String idCardNo;

  /** 手机号 */
  @TableField(value = "phone_number")
  private String phoneNumber;

  /** 认证类型 */
  @TableField(value = "cert_type")
  private String certType;

  /** 其余资料 */
  @TableField(value = "imgs")
  private String imgs;

  /** 认证状态 */
  @TableField(value = "cert_status")
  private Integer certStatus;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

  @TableField(value = "is_delete")
  @TableLogic
  private Integer isDelete;

  public static final String COL_ID = "id";

  public static final String COL_USER_ID = "user_id";

  public static final String COL_REAL_NAME = "real_name";

  public static final String COL_ID_CARD_NO = "id_card_no";

  public static final String COL_PHONE_NUMBER = "phone_number";

  public static final String COL_CERT_TYPE = "cert_type";

  public static final String COL_IMGS = "imgs";

  public static final String COL_CERT_STATUS = "cert_status";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
