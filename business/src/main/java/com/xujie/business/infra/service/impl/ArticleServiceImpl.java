package com.xujie.business.infra.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.business.infra.DO.BizArticle;
import com.xujie.business.infra.mapper.BizArticleMapper;
import com.xujie.business.infra.service.ArticleService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文章服务实现
 *
 * @author Xujie
 * @since 2024/10/13 20:24
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
  @Resource private BizArticleMapper articleMapper;

  /**
   * 展示文章
   *
   * @param num 文章数量
   * @return 文章
   */
  @Override
  public List<BizArticle> showArticle(Integer num) {
    LambdaQueryWrapper<BizArticle> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.orderByDesc(BizArticle::getCreateTime);
    lambdaQueryWrapper.last("limit " + num);
    return articleMapper.selectList(lambdaQueryWrapper);
  }

  /**
   * \ 获取文章列表
   *
   * @param bizArticle 文章实体
   * @return 文章列表
   */
  @Override
  public List<BizArticle> getArticleListByEntity(BizArticle bizArticle) {
    LambdaQueryWrapper<BizArticle> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(
        ObjectUtil.isNotNull(bizArticle.getId()), BizArticle::getId, bizArticle.getId());
    lambdaQueryWrapper.orderByAsc(BizArticle::getArticleRank);
    lambdaQueryWrapper.orderByDesc(BizArticle::getCreateTime, BizArticle::getUpdateTime);
    return articleMapper.selectList(lambdaQueryWrapper);
  }

  /**
   * 获取文章
   *
   * @param bizArticle 文章实体
   * @return 文章
   */
  @Override
  public BizArticle getArticleByEntity(BizArticle bizArticle) {
    return getArticleListByEntity(bizArticle).stream().findFirst().orElse(null);
  }
}
