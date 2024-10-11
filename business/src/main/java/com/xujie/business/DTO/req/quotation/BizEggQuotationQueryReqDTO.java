package com.xujie.business.DTO.req.quotation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报价查询请求DTO
 *
 * @author Xujie
 * @since 2024/10/9 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotationQueryReqDTO {
  /** 报价地址 */
  @Pattern(regexp = "[\\u4e00-\\u9fa5]+", message = "报价地址必须为中文")
  private String quotationLocation;

  /** 报价类型 */
  private Integer quotationType;

  /** 蛋类型ID */
  private Integer eggTypeId;

  @NotNull(message = "pageNum不能为空")
  private Integer pageNum;

  @NotNull(message = "pageSize不能为空")
  private Integer pageSize;
}
