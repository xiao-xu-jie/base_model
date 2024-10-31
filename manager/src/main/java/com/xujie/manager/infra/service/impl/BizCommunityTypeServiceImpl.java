package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizCommunityPostType;
import com.xujie.manager.infra.mapper.BizCommunityPostTypeMapper;
import com.xujie.manager.infra.service.BizCommunityTypeService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Xujie
 * @since 2024/10/31 23:05
 */
@Slf4j
@Service
public class BizCommunityTypeServiceImpl implements BizCommunityTypeService {
  @Resource private BizCommunityPostTypeMapper bizCommunityPostTypeMapper;

  @Override
  public Long addOne(BizCommunityPostType baseDO) {
    return bizCommunityPostTypeMapper.insert(baseDO) > 0 ? baseDO.getId() : null;
  }

  @Override
  public BizCommunityPostType getOneByEntity(BizCommunityPostType baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizCommunityPostType> getListByEntity(BizCommunityPostType baseDO) {
    QueryWrapper<BizCommunityPostType> bizCommunityPostTypeQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizCommunityPostTypeMapper.selectList(bizCommunityPostTypeQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizCommunityPostTypeMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizCommunityPostType baseDO) {
    return bizCommunityPostTypeMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizCommunityPostType> getPageList(
      BizCommunityPostType baseDO, Integer pageNum, Integer pageSize) {
    QueryWrapper<BizCommunityPostType> bizCommunityPostTypeQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizCommunityPostTypeMapper.selectPage(
        new Page<>(pageNum, pageSize), bizCommunityPostTypeQueryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizCommunityPostTypeMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
