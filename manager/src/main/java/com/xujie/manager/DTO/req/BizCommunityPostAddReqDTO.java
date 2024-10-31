package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BizCommunityPost)添加DTO
 *
 * @author xujie
 * @since 2024-10-31 23:48:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizCommunityPostAddReqDTO extends BaseDTO {

  /** 发布ID */
  private Long id;

  /** 用户ID */
  private Long userId;

  /** 发布类型ID */
  private Long postTypeId;

  /** 发布类型名称 */
  private String postTypeName;

  /** 发布标题 */
  private String title;

  private String postDesc;

  /** 内容 */
  private String content;

  /** 封面 */
  private String coverImg;

  /** 图片数组 */
  private String imgArray;

  /** 状态 0,1 */
  private Integer status;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
