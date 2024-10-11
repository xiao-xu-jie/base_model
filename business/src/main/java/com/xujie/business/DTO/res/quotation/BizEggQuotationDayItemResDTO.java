package com.xujie.business.DTO.res.quotation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotationDayItemResDTO {
  private Long typeId;

  private Double avgSaleNum;

  /** 平均价 */
  private Double avgNum;

  /** 最高价 */
  private Double maxNum;

  /** 最低价 */
  private Double minNum;

  /** 报价人数 */
  private Integer peopleNumber;

  /** 下降，持平，上升-1,0,1 */
  private Integer status;

  private String dataTime;
}
