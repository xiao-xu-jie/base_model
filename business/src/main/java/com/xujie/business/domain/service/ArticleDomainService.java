package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizArticleBO;
import java.util.List;

public interface ArticleDomainService {
  /**
   * 展示文章
   *
   * @param num
   * @return
   */
  List<BizArticleBO> showArticle(Integer num);

  BizArticleBO showArticleById(Long id);
}
