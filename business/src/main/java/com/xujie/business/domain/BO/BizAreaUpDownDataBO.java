package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 区域上涨下涨数据业务对象
 *
 * @author Xujie
 * @since 2024/10/8 17:30
 */
@Data
@AllArgsConstructor
public class BizAreaUpDownDataBO {
  BizAreaUpDownDataItemBO saleData;
  BizAreaUpDownDataItemBO buyData;

  public BizAreaUpDownDataBO() {
    this.saleData = new BizAreaUpDownDataItemBO();
    this.buyData = new BizAreaUpDownDataItemBO();
  }
}
