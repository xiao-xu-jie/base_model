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
public class BizEggType {
  /** 类型ID */
  @NotNull(message = "类型ID不能为null")
  private Long id;

  /** 类型名称 */
  @Size(max = 255, message = "类型名称最大长度要小于 255")
  @NotBlank(message = "类型名称不能为空")
  private String eggTypeName;

  @NotNull(message = "不能为null")
  private Date createTime;

  @NotNull(message = "不能为null")
  private Date updateTime;

  @NotNull(message = "不能为null")
  private Integer isDelete;
}
