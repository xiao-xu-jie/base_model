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
public class BizCommunityPostType {
  /** 发布类型id */
  @NotNull(message = "发布类型id不能为null")
  private Long id;

  /** 发布类型名称 */
  @Size(max = 255, message = "发布类型名称最大长度要小于 255")
  private String postTypeName;

  /** 发布类型描述 */
  @Size(max = 255, message = "发布类型描述最大长度要小于 255")
  private String postTypeDesc;

  /** 发布类型图片 */
  @Size(max = 255, message = "发布类型图片最大长度要小于 255")
  @NotBlank(message = "发布类型图片不能为空")
  private String postTypeImg;

  /** 发布类型状态 0,1 */
  @NotNull(message = "发布类型状态 0,1不能为null")
  private Integer postTypeStatus;

  @NotNull(message = "不能为null")
  private Date createTime;

  @NotNull(message = "不能为null")
  private Date updateTime;

  @NotNull(message = "不能为null")
  private Integer isDelete;
}
