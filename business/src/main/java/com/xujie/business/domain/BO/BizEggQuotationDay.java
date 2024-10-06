package com.xujie.business.domain.BO;

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
  private Long id;

  private Long typeId;

  /** 平均价 */
  private Double avgNum;

  /** 最高价 */
  private Double maxNum;

  /** 最低价 */
  private Double minNum;

  /** 报价人数 */
  private Integer peopleNumber;

  /** 下降，持平，上升-1,0,1 */
  private Integer status;

  private Date dataTime;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
