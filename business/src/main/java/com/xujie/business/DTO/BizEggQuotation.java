package com.xujie.business.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotation {
  /** 报价ID */
  @NotNull(message = "报价ID不能为null")
  private Long id;

  /** 用户ID */
  @NotNull(message = "用户ID不能为null")
  private Long userId;

  /** 蛋类型ID */
  @NotNull(message = "蛋类型ID不能为null")
  private Long eggTypeId;

  /** 蛋类型名称 */
  @Size(max = 255, message = "蛋类型名称最大长度要小于 255")
  @NotBlank(message = "蛋类型名称不能为空")
  private String eggTypeName;

  /** 报价地址 */
  @Size(max = 255, message = "报价地址最大长度要小于 255")
  private String quotationLocation;

  /** 是否显示 */
  @NotNull(message = "是否显示不能为null")
  private Integer quotationStatus;

  /** 0-收购价，1-出售价 */
  private Integer quotationType;

  /** 发布日期（yyyy_mm_dd) */
  private Date dataDate;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
