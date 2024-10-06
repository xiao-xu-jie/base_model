package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizEggTypeBO {
  /** 类型ID */
  private Long id;

  /** 类型名称 */
  private String eggTypeName;
}
