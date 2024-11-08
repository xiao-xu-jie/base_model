package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizArticle;
import com.xujie.manager.infra.mapper.BizArticleMapper;
import com.xujie.manager.infra.service.BizArticleService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文章表(BizArticle)表服务实现类
 *
 * @author Xujie
 * @since 2024/11/8 08:38
 */
@Slf4j
@Service
public class BizArticleServiceImpl implements BizArticleService {
  @Resource private BizArticleMapper bizArticleMapper;

  @Override
  public Long addOne(BizArticle baseDO) {
    return bizArticleMapper.insert(baseDO) > 0 ? baseDO.getId() : 0;
  }

  @Override
  public BizArticle getOneByEntity(BizArticle baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizArticle> getListByEntity(BizArticle baseDO) {
    QueryWrapper<BizArticle> bizArticleQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizArticleMapper.selectList(bizArticleQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizArticleMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizArticle baseDO) {
    return bizArticleMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizArticle> getPageList(BizArticle baseDO, Integer pageNum, Integer pageSize) {
    Page<BizArticle> page = new Page<>(pageNum, pageSize);
    QueryWrapper<BizArticle> bizArticleQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizArticleMapper.selectPage(page, bizArticleQueryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizArticleMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
