package com.xujie.business.DTO;

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
public class BizCommunityPost {
  /** 发布ID */
  @NotNull(message = "发布ID不能为null")
  private Long id;

  /** 用户ID */
  @NotNull(message = "用户ID不能为null")
  private Long userId;

  /** 发布类型ID */
  private Long postTypeId;

  /** 发布类型名称 */
  @Size(max = 255, message = "发布类型名称最大长度要小于 255")
  private String postTypeName;

  /** 发布标题 */
  @Size(max = 255, message = "发布标题最大长度要小于 255")
  private String title;

  /** 内容 */
  @Size(max = 255, message = "内容最大长度要小于 255")
  private String content;

  /** 图片数组 */
  @Size(max = 255, message = "图片数组最大长度要小于 255")
  private String imgArray;

  /** 状态 0,1 */
  @NotNull(message = "状态 0,1不能为null")
  private Integer status;

  @NotNull(message = "不能为null")
  private Date createTime;

  private Date updateTime;

  @NotNull(message = "不能为null")
  private Integer isDelete;
}
