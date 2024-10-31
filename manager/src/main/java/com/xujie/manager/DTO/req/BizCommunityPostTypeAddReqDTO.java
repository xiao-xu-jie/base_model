package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BizCommunityPostType)添加DTO
 *
 * @author xujie
 * @since 2024-10-31 23:02:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizCommunityPostTypeAddReqDTO extends BaseDTO {

  /** 发布类型id */
  private Long id;

  /** 发布类型名称 */
  private String postTypeName;

  /** 发布类型描述 */
  private String postTypeDesc;

  /** 发布类型icon */
  private String postTypeIcon;

  /** 发布类型图片 */
  private String postTypeImg;

  /** 发布类型状态 0,1 */
  private Integer postTypeStatus;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
