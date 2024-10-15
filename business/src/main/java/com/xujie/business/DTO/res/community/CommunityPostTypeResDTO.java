package com.xujie.business.DTO.res.community;

import com.xujie.business.common.enums.ShowStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区发布类型返回DTO
 *
 * @author Xujie
 * @since 2024/10/14 13:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostTypeResDTO {
  /** 发布类型id */
  private Long id;

  /** 发布类型名称 */
  private String postTypeName;

  /** 发布类型描述 */
  private String postTypeDesc;

  /** 发布类型图片 */
  private String postTypeImg;

  /** 发布类型状态 0,1 */
  private ShowStatusEnum postTypeStatus;
}
