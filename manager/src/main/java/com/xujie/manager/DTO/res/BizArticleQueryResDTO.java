package com.xujie.manager.DTO.res;

import com.xujie.manager.common.base.model.BaseDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizArticle)查询返回DTO
 *
 * @author xujie
 * @since 2024-11-08 08:40:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizArticleQueryResDTO extends BaseDTO {

  private Long id;

  /** 略缩图 */
  private String articleShowImg;

  /** 文章标题 */
  private String articleTitle;

  /** 文章描述 */
  private String articleDesc;

  /** 文章内容 */
  private String articleContent;

  /** 文章来源 */
  private String articleSource;

  /** 文章额外信息 */
  private String articleExtra;

  /** 是否显示 */
  private Integer isShow;

  /** 文章排序 */
  private Integer articleRank;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
