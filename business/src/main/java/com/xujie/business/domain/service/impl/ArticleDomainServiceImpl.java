package com.xujie.business.domain.service.impl;

import com.xujie.business.convert.ArticleConvert;
import com.xujie.business.domain.BO.BizArticleBO;
import com.xujie.business.domain.service.ArticleDomainService;
import com.xujie.business.infra.DO.BizArticle;
import com.xujie.business.infra.service.ArticleService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文章领域服务
 *
 * @author Xujie
 * @since 2024/10/13 20:31
 */
@Slf4j
@Service
public class ArticleDomainServiceImpl implements ArticleDomainService {
  @Resource private ArticleService articleService;
  @Resource private ArticleConvert articleConvert;

  /**
   * 展示文章
   *
   * @param num 文章数量
   * @return 文章
   */
  @Override
  public List<BizArticleBO> showArticle(Integer num) {
    List<BizArticle> bizArticles = articleService.showArticle(num);
    return articleConvert.convertArticleDOList2BOList(bizArticles);
  }

  @Override
  public BizArticleBO showArticleById(Long id) {
    BizArticle articleByEntity =
        articleService.getArticleByEntity(BizArticle.builder().id(id).build());
    return articleConvert.convertArticleDO2BO(articleByEntity);
  }
}
