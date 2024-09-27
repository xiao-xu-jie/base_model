package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizCategory;
import com.xujie.manager.infra.mapper.BizCategoryMapper;
import com.xujie.manager.infra.service.BizCategoryService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 分类服务实现
 *
 * @author Xujie
 * @since 2024/9/27 19:10
 */
@Slf4j
@Service
public class BizCategoryServiceImpl implements BizCategoryService {
  @Resource private BizCategoryMapper baseMapper;

  @Override
  public Long addOne(BizCategory baseDO) {
    return baseMapper.insert(baseDO) > 0 ? baseDO.getId() : null;
  }

  @Override
  public BizCategory getOneByEntity(BizCategory baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizCategory> getListByEntity(BizCategory baseDO) {
    return baseMapper.getByAll(baseDO);
  }

  @Override
  public boolean deleteOne(Long id) {
    return deleteBatch(new Long[] {id});
  }

  @Override
  public boolean updateOne(Long id, BizCategory baseDO) {
    return baseMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizCategory> getPageList(BizCategory baseDO, Integer pageNum, Integer pageSize) {
    LambdaQueryWrapper<BizCategory> query = new LambdaQueryWrapper<>();
    query.eq(baseDO.getId() != null, BizCategory::getId, baseDO.getId());
    query.like(
        baseDO.getCategoryName() != null, BizCategory::getCategoryName, baseDO.getCategoryName());
    query.eq(
        baseDO.getCategoryStatus() != null,
        BizCategory::getCategoryStatus,
        baseDO.getCategoryStatus());
    return baseMapper.selectPage(new Page<>(pageNum, pageSize), query);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return baseMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
