package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.manager.common.base.model.BaseDO;
import java.util.Date;
import lombok.*;

/**
 * (BizSourceStation)表实体类
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "biz_source_station")
public class BizSourceStation extends BaseDO {

  public static final String COL_id = "id";
  public static final String COL_stationName = "station_name";
  public static final String COL_stationUrl = "station_url";
  public static final String COL_uid = "uid";
  public static final String COL_secret = "secret";
  public static final String COL_stationStatus = "station_status";
  public static final String COL_createTime = "create_time";
  public static final String COL_updateTime = "update_time";
  public static final String COL_isDelete = "is_delete";

  /** 货源站ID */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 货源站名称 */
  @TableField(value = "station_name")
  private String stationName;

  /** 货源站地址 */
  @TableField(value = "station_url")
  private String stationUrl;

  /** uid */
  @TableField(value = "uid")
  private String uid;

  /** 对接key */
  @TableField(value = "secret")
  private String secret;

  /** 站点状态 */
  @TableField(value = "station_status")
  private Integer stationStatus;

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
