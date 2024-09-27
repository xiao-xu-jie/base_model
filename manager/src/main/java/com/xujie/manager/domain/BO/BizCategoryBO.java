package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizCategory)BO
 *
 * @author xujie
 * @since 2024-09-27 19:02:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizCategoryBO extends BaseBO {

  /** 分类ID */
  private Long id;

  /** 分类名称 */
  private String categoryName;

  /** 分类排序 */
  private Integer categoryRank;

  /** 分类描述 */
  private String categoryDesc;

  /** 分类状态 */
  private Integer categoryStatus;

  /** 创建时间 */
  private Date createTime;

  /** 更新时间 */
  private Date updateTime;

  /** 是否删除 */
  private Integer isDelete;
}
