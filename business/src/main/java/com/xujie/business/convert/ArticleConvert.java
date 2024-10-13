package com.xujie.business.convert;

import com.xujie.business.DTO.res.article.ArticleShowResDTO;
import com.xujie.business.domain.BO.BizArticleBO;
import com.xujie.business.infra.DO.BizArticle;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleConvert {
  List<BizArticleBO> convertArticleDOList2BOList(List<BizArticle> bizArticles);

  BizArticleBO convertArticleDO2BO(BizArticle articleByEntity);

  List<ArticleShowResDTO> convertBizArticleBOList2ArticleShowResDTOList(
      List<BizArticleBO> bizArticleBOS);
}
