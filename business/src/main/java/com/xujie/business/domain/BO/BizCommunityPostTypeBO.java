package com.xujie.business.domain.BO;

import com.xujie.business.common.enums.ShowStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizCommunityPostTypeBO {
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
