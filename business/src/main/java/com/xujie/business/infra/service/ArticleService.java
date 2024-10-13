package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizArticle;
import java.util.List;

public interface ArticleService {
  /**
   * 展示文章
   *
   * @param num 文章数量
   * @return 文章
   */
  List<BizArticle> showArticle(Integer num);

  /**
   * \ 获取文章列表
   *
   * @param bizArticle 文章实体
   * @return 文章列表
   */
  List<BizArticle> getArticleListByEntity(BizArticle bizArticle);

  /**
   * 获取文章
   *
   * @param bizArticle 文章实体
   * @return 文章
   */
  BizArticle getArticleByEntity(BizArticle bizArticle);
}
