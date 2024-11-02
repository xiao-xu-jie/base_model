package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizUserVip;
import com.xujie.manager.infra.mapper.BizUserVipMapper;
import com.xujie.manager.infra.service.BizUserVipService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizUserVip)表服务实现类
 *
 * @since 2024/10/31 10:51
 */
@Slf4j
@Service
public class BizUserVipServiceImpl implements BizUserVipService {
  @Resource private BizUserVipMapper bizUserVipMapper;

  @Override
  public Long addOne(BizUserVip baseDO) {
    return bizUserVipMapper.insert(baseDO) > 0 ? baseDO.getVipId() : 0L;
  }

  @Override
  public BizUserVip getOneByEntity(BizUserVip baseDO) {
    QueryWrapper<BizUserVip> bizUserVipQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizUserVipMapper.selectOne(bizUserVipQueryWrapper);
  }

  @Override
  public List<BizUserVip> getListByEntity(BizUserVip baseDO) {
    QueryWrapper<BizUserVip> bizUserVipQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizUserVipMapper.selectList(bizUserVipQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return false;
  }

  @Override
  public boolean updateOne(Long id, BizUserVip baseDO) {
    return false;
  }

  @Override
  public Page<BizUserVip> getPageList(BizUserVip baseDO, Integer pageNum, Integer pageSize) {
    return null;
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return false;
  }

  @Override
  public List<BizUserVip> getListByUserIds(List<Long> userIds) {
    if (userIds == null || userIds.isEmpty()) {
      return Lists.newArrayList();
    }
    QueryWrapper<BizUserVip> queryWrapper = new QueryWrapper<>();
    queryWrapper.in(BizUserVip.COL_USER_ID, userIds);
    return bizUserVipMapper.getUserVipList(queryWrapper);
  }

  @Override
  public void deleteUserVip(Long id) {
    QueryWrapper<BizUserVip> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq(BizUserVip.COL_USER_ID, id);
    bizUserVipMapper.delete(queryWrapper);
  }
}
