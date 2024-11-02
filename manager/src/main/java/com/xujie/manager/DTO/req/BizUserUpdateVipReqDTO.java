package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BizUser)更新VipDTO
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizUserUpdateVipReqDTO extends BaseDTO {

  /** 用户ID */
  private Long id;

  private Long vipId;
}
