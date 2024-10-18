package com.xujie.business.DTO.res.quotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询报价响应DTO
 *
 * @author Xujie
 * @since 2024/10/18 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizUserQueryQuotationResDTO {
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

  /** -1-下浮，0-正常，1-上浮 */
  private Integer quotationFloatStatus;

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
