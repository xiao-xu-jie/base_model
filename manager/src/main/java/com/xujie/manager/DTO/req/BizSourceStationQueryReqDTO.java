package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizSourceStation)查询请求DTO
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizSourceStationQueryReqDTO extends BaseDTO {

  /** 货源站ID */
  private Long id;

  /** 货源站名称 */
  @NotBlank(message = "货源站名称不能为空")
  private String stationName;

  /** 货源站地址 */
  @NotBlank(message = "货源站地址不能为空")
  private String stationUrl;

  /** uid */
  @NotBlank(message = "uid不能为空")
  private String uid;

  /** 对接key */
  @NotBlank(message = "对接key不能为空")
  private String secret;

  /** 站点状态 */
  @NotNull(message = "站点状态不能为空")
  private Integer stationStatus;
}
