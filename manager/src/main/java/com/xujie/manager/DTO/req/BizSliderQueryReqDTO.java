package com.xujie.manager.DTO.req;

import com.xujie.manager.common.base.model.BaseDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 发布页面轮播(BizSlider)查询请求DTO
 *
 * @author xujie
 * @since 2024-11-07 09:07:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizSliderQueryReqDTO extends BaseDTO {

  /** 幻灯片ID */
  private Long id;

  /** 内容 */
  private String content;

  /** 图片内容 */
  private String imgUrl;

  /** 是否显示 */
  private Integer showStatus;

  private String params;

  /** 排序 */
  private Integer rank;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
