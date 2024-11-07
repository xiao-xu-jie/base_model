package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 发布页面轮播(BizSlider)BO
 *
 * @author xujie
 * @since 2024-11-07 09:07:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizSliderBO extends BaseBO {

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
