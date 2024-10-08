package com.xujie.business.DTO.res.quotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域上涨下涨数据DTO
 *
 * @author Xujie
 * @since 2024/10/8 17:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizAreaUpDownDataResDTO {
  BizAreaUpDownDataItemDTO saleData;
  BizAreaUpDownDataItemDTO buyData;
}
