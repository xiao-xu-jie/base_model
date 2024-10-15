package com.xujie.business.DTO.req.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区帖子查询请求DTO
 *
 * @author Xujie
 * @since 2024/10/14 12:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostQueryReqDTO {
  private Long id;

  private Long postTypeId;

  private String searchText;
  private Integer pageNum;
  private Integer pageSize;
}
