package com.xujie.manager.DTO.res;

import com.xujie.manager.common.base.model.BaseDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizCategory)查询返回DTO
 *
 * @author xujie
 * @since 2024-09-27 19:02:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizCategoryQueryResDTO extends BaseDTO {

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
