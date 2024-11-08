package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizArticleBO;
import com.xujie.manager.domain.convert.BizArticleConvert;
import com.xujie.manager.domain.service.BizArticleDomainService;
import com.xujie.manager.infra.DO.BizArticle;
import com.xujie.manager.infra.service.BizArticleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizArticle)表服务实现类
 *
 * @author xujie
 * @since 2024-11-08 08:40:33
 */
@Slf4j
@Service
public class BizArticleDomainServiceImpl implements BizArticleDomainService {
  @Resource private BizArticleService bizArticleService;
  @Resource private BizArticleConvert bizArticleConvert;

  @Override
  public void add(BizArticleBO bizArticleBO) {
    bizArticleService.addOne(bizArticleConvert.convertBO2DO(bizArticleBO));
  }

  @Override
  public Page<BizArticleBO> getPageList(
      BizArticleBO bizArticleBO, Integer pageNum, Integer pageSize) {
    BizArticle bizArticle = bizArticleConvert.convertBO2DO(bizArticleBO);
    return bizArticleConvert.convertPageDO2BO(
        bizArticleService.getPageList(bizArticle, pageNum, pageSize));
  }

  @Override
  public void delete(Long[] ids) {
    bizArticleService.deleteBatch(ids);
  }

  @Override
  public void update(BizArticleBO bizArticleBO) {
    bizArticleService.updateOne(bizArticleBO.getId(), bizArticleConvert.convertBO2DO(bizArticleBO));
  }
}
