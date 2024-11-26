package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xujie
 * @since 2024/11/26 20:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryClassBO {
  /** 商品id */
  private Long good_id;

  /** 手机号 */
  private String user;

  /** 密码 */
  private String pass;
}
