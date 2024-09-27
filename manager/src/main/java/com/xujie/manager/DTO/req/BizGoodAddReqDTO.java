package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import com.xujie.manager.common.entity.Groups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BizGood)添加DTO
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizGoodAddReqDTO extends BaseDTO {

  /** 商品ID */
  private Long id;

  /** 分类id */
  @NotNull(
      message = "分类id不能为空",
      groups = {Groups.Add.class, Groups.Update.class})
  private Long categoryId;

  /** 商品名称 */
  @NotBlank(
      message = "商品名称不能为空",
      groups = {Groups.Add.class, Groups.Update.class})
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
  @NotNull(
      message = "平台ID不能为空",
      groups = {Groups.Add.class, Groups.Update.class})
  private Integer platformId;

  /** 货源站ID */
  @NotNull(
      message = "货源站ID不能为空",
      groups = {Groups.Add.class, Groups.Update.class})
  private Long stationId;
}
