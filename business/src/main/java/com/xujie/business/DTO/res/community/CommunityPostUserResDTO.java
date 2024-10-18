package com.xujie.business.DTO.res.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区帖子查询响应DTO
 *
 * @author Xujie
 * @since 2024/10/18 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostUserResDTO {
  private Long id;

  private Long userId;

  private Long postTypeId;

  private String postTypeName;

  private String title;

  private String content;

  /** 描述 */
  private String postDesc;

  private String coverImg;

  private String imgArray;
}
