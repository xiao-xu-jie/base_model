package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域上涨下涨数据业务对象
 *
 * @author Xujie
 * @since 2024/10/8 17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizAreaUpDownDataItemBO {
  private double upPercent;
  private double downPercent;
  private double flatPercent;
}
