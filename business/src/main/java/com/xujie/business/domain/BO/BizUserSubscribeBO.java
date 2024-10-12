package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户订阅
 *
 * @author Xujie
 * @since 2024/10/12 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizUserSubscribeBO {
  /** 用户ID */
  private Long userId;

  /** 订阅的用户ID */
  private Long subUserId;
}
