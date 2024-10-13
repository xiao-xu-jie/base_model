package com.xujie.business.domain.BO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章业务对象
 *
 * @author Xujie
 * @since 2024/10/13 20:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizArticleBO {
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

  private Date createTime;
}
