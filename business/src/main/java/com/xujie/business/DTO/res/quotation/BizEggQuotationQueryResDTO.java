package com.xujie.business.DTO.res.quotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xujie
 * @since 2024/10/9 08:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotationQueryResDTO {

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

  private Long userId;
  private String jsonArr;

  private String userJson;
}
