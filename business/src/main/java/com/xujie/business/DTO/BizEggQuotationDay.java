package com.xujie.business.DTO;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotationDay {
  @NotNull(message = "不能为null")
  private Long id;

  @NotNull(message = "不能为null")
  private Long typeId;

  /** 平均价 */
  @NotNull(message = "平均价不能为null")
  private BigDecimal avgNum;

  /** 最高价 */
  @NotNull(message = "最高价不能为null")
  private BigDecimal maxNum;

  /** 最低价 */
  @NotNull(message = "最低价不能为null")
  private BigDecimal minNum;

  /** 报价人数 */
  @NotNull(message = "报价人数不能为null")
  private Integer peopleNumber;

  /** 下降，持平，上升-1,0,1 */
  @NotNull(message = "下降，持平，上升-1,0,1不能为null")
  private Integer status;

  @NotNull(message = "不能为null")
  private Date dataTime;

  @NotNull(message = "不能为null")
  private Date createTime;

  @NotNull(message = "不能为null")
  private Date updateTime;

  @NotNull(message = "不能为null")
  private Integer isDelete;
}
