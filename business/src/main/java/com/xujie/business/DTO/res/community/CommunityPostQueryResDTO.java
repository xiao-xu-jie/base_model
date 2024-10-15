package com.xujie.business.DTO.res.community;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区帖子查询响应DTO
 *
 * @author Xujie
 * @since 2024/10/14 12:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostQueryResDTO {
  private Long id;

  private Long userId;

  private Long postTypeId;

  private String postTypeName;

  private String title;

  private String content;

  private String coverImg;

  private String imgArray;

  private String userInfoJson;

  private Date createTime;
}
