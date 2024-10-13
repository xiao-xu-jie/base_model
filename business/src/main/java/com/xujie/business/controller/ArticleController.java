package com.xujie.business.controller;

import com.xujie.business.DTO.res.article.ArticleShowResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.ArticleConvert;
import com.xujie.business.domain.BO.BizArticleBO;
import com.xujie.business.domain.service.ArticleDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章控制器
 *
 * @author Xujie
 * @since 2024/10/13 20:33
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
  @Resource private ArticleDomainService articleDomainService;
  @Resource private ArticleConvert articleConvert;

  /**
   * 展示文章
   *
   * @return 文章
   */
  @GetMapping("/show")
  public Result<List<ArticleShowResDTO>> showArticle() {
    List<BizArticleBO> bizArticleBOS = articleDomainService.showArticle(2);
    return Result.ok(articleConvert.convertBizArticleBOList2ArticleShowResDTOList(bizArticleBOS));
  }
}
