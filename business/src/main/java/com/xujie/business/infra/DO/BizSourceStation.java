package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_source_station")
public class BizSourceStation {
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
  @TableField(value = "`uid`")
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
  private Integer isDelete;

  public static final String COL_ID = "id";

  public static final String COL_STATION_NAME = "station_name";

  public static final String COL_STATION_URL = "station_url";

  public static final String COL_UID = "uid";

  public static final String COL_KEY = "key";

  public static final String COL_STATION_STATION = "station_station";

  public static final String COL_CREATE_TIME = "create_time";

  public static final String COL_UPDATE_TIME = "update_time";

  public static final String COL_IS_DELETE = "is_delete";
}
