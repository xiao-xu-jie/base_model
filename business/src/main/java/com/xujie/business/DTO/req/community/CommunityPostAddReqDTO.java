package com.xujie.business.DTO.req.community;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区帖子查询响应DTO
 *
 * @author Xujie
 * @since 2024/10/15 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostAddReqDTO {

  @NotNull(message = "参数非法")
  private Long postTypeId;

  @NotBlank(message = "参数非法")
  private String postTypeName;

  @NotBlank(message = "标题不能为空")
  private String title;

  @NotBlank(message = "内容不能为空")
  private String content;

  /** 描述 */
  @NotBlank(message = "描述不能为空")
  private String postDesc;

  private String coverImg;
}
