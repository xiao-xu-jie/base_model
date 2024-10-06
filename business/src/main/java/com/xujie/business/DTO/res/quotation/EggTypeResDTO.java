package com.xujie.business.DTO.res.quotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 蛋类型响应DTO
 *
 * @author Xujie
 * @since 2024/10/6 13:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EggTypeResDTO {
  /** 类型ID */
  private Long id;

  /** 类型名称 */
  private String eggTypeName;
}
