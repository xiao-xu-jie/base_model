package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotationBO {
  /** 报价ID */
  private Long id;

  /** 用户ID */
  private Long userId;

  /** 蛋类型ID */
  private Long eggTypeId;

  /** 蛋类型名称 */
  private String eggTypeName;

  /** 报价地址 */
  private String quotationLocation;

  /** 是否显示 */
  private Integer quotationStatus;

  /** 0-收购价，1-出售价 */
  private Integer quotationType;

  /** 最高价 */
  private Double quotationMaxPrice;

  /** 参考价 */
  private Double quotationAvgPrice;

  /** 最低价 */
  private Double quotationMinPrice;

  /** 发布日期（yyyy_mm_dd) */
  private String dataDate;
}
