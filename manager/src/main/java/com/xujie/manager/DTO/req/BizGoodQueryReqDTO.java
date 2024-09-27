package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizGood)查询请求DTO
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizGoodQueryReqDTO extends BaseDTO {

  /** 商品ID */
  private Long id;

  /** 分类id */
  @NotBlank(message = "分类id不能为空")
  private Long categoryId;

  /** 商品名称 */
  private String goodName;

  /** 商品描述 */
  private String goodDesc;

  /** 商品价格 */
  @NotNull(message = "商品价格不能为空")
  private BigDecimal goodPrice;

  /** 商品排序 */
  private Integer goodRank;

  /** 商品参数 */
  private Object goodParams;

  /** 平台ID */
  @NotNull(message = "平台ID不能为空")
  private Integer platformId;

  /** 货源站ID */
  private Long stationId;
}
