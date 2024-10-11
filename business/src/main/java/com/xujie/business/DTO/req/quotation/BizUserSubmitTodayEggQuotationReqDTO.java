package com.xujie.business.DTO.req.quotation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户提交今日报价请求DTO
 *
 * @author Xujie
 * @since 2024/10/6 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizUserSubmitTodayEggQuotationReqDTO {
  /** 蛋类型ID */
  @NotNull(message = "报价类型不能为空")
  private Long eggTypeId;

  /** 蛋类型名称 */
  @NotBlank(message = "报价类型名称不能为空")
  private String eggTypeName;

  /** 报价地址 */
  @NotBlank(message = "报价地址不能为空")
  private String quotationLocation;

  /** 是否显示 */
  //  @NotNull(message = "报价类型不能为空")
  private Integer quotationStatus;

  /** 0-收购价，1-出售价 */
  @NotNull(message = "报价类型不能为空")
  private Integer quotationType;

  /** 最高价 */
  @NotNull(message = "最高价不能为空")
  private Double quotationMaxPrice;

  /** 参考价 */
  @NotNull(message = "参考价不能为空")
  private Double quotationAvgPrice;

  /** 最低价 */
  @NotNull(message = "最低价不能为空")
  private Double quotationMinPrice;
}
