package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizCommunityPost;
import com.xujie.manager.infra.mapper.BizCommunityPostMapper;
import com.xujie.manager.infra.service.BizCommunityService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizCommunityPost)表服务实现类
 *
 * @author Xujie
 * @since 2024/10/31 23:56
 */
@Slf4j
@Service
public class BizCommunityServiceImpl implements BizCommunityService {
  @Resource private BizCommunityPostMapper bizCommunityPostMapper;

  @Override
  public Long addOne(BizCommunityPost baseDO) {
    return bizCommunityPostMapper.insert(baseDO) > 0 ? baseDO.getId() : 0;
  }

  @Override
  public BizCommunityPost getOneByEntity(BizCommunityPost baseDO) {
    return null;
  }

  @Override
  public List<BizCommunityPost> getListByEntity(BizCommunityPost baseDO) {
    QueryWrapper<BizCommunityPost> bizCommunityPostQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizCommunityPostMapper.selectList(bizCommunityPostQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizCommunityPostMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizCommunityPost baseDO) {
    return bizCommunityPostMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizCommunityPost> getPageList(
      BizCommunityPost baseDO, Integer pageNum, Integer pageSize) {
    Page<BizCommunityPost> page = new Page<>(pageNum, pageSize);
    QueryWrapper<BizCommunityPost> bizCommunityPostQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    bizCommunityPostQueryWrapper.orderByDesc("create_time", "update_time");
    return bizCommunityPostMapper.selectPage(page, bizCommunityPostQueryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizCommunityPostMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
