package com.xujie.business.domain.BO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区帖子
 *
 * @author Xujie
 * @since 2024/10/14 12:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizCommunityPostBO {
  private Long id;

  private Long userId;

  private Long postTypeId;

  private String postTypeName;

  private String title;

  private String content;

  private String coverImg;

  private String imgArray;

  private Date createTime;

  private String userInfoJson;

  private String searchText;
}
