package com.xujie.business.DTO.res.quotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域上涨下涨数据DTO
 *
 * @author Xujie
 * @since 2024/10/8 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizAreaUpDownDataItemDTO {
  private double upPercent;
  private double downPercent;
  private double flatPercent;
}
