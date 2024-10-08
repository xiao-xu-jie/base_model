package com.xujie.business.domain.BO;

import com.xujie.business.infra.DO.BizEggQuotationDay;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 周数据业务对象
 *
 * @author Xujie
 * @since 2024/10/8 13:03
 */
@Data
@AllArgsConstructor
public class BizWeekDataBO {
  /** 出售价 */
  private List<BizEggQuotationDay> salesList;

  /** 收购价 */
  private List<BizEggQuotationDay> buyList;

  public BizWeekDataBO() {
    salesList = new ArrayList<>(7);
    buyList = new ArrayList<>(7);
    for (int i = 0; i < 7; i++) {
      salesList.add(null);
      buyList.add(null);
    }
  }
}
