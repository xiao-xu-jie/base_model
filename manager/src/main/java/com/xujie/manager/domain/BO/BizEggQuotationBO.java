package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizEggQuotation)BO
 *
 * @author xujie
 * @since 2024-11-01 23:29:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotationBO extends BaseBO {

  /** 报价ID */
  private Long id;

  /** 用户ID */
  private Long userId;

  private String nickName;

  /** 蛋类型ID */
  private Long eggTypeId;

  /** 蛋类型名称 */
  private String eggTypeName;

  /** 报价地址 */
  private String quotationLocation;

  private Double quotationMaxPrice;

  /** 参考价 */
  private Double quotationAvgPrice;

  /** 最低价 */
  private Double quotationMinPrice;

  /** 是否显示 */
  private Integer quotationStatus;

  /** 波动 */
  private Integer quotationFloatStatus;

  /** 0-收购价，1-出售价 */
  private Integer quotationType;

  /** 发布日期（yyyy_mm_dd) */
  private String dataDate;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
