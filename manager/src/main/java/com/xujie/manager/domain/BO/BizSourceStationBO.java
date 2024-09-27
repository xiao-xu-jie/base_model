package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizSourceStation)BO
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizSourceStationBO extends BaseBO {

  /** 货源站ID */
  private Long id;

  /** 货源站名称 */
  private String stationName;

  /** 货源站地址 */
  private String stationUrl;

  /** uid */
  private String uid;

  /** 对接key */
  private String secret;

  /** 站点状态 */
  private Integer stationStatus;

  /** 创建时间 */
  private Date createTime;

  /** 更新时间 */
  private Date updateTime;

  /** 是否删除 */
  private Integer isDelete;
}
