package com.xujie.manager.DTO.res;

import com.xujie.manager.common.base.model.BaseDTO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizGood)查询返回DTO
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizGoodQueryResDTO extends BaseDTO {

  /** 商品ID */
  private Long id;

  /** 分类id */
  private Long categoryId;

  /** 商品名称 */
  private String goodName;

  /** 商品描述 */
  private String goodDesc;

  /** 商品价格 */
  private BigDecimal goodPrice;

  /** 商品排序 */
  private Integer goodRank;

  /** 商品参数 */
  private Object goodParams;

  /** 平台ID */
  private Integer platformId;

  /** 货源站ID */
  private Long stationId;

  /** 创建时间 */
  private Date createTime;

  /** 更新时间 */
  private Date updateTime;

  /** 是否删除 */
  private Integer isDelete;

  /** 分类名称 */
  private String categoryName;

  /** 货源名称 */
  private String stationName;
}
