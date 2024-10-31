package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.mapper.BizUserMapper;
import com.xujie.manager.infra.service.BizUserService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * (BizUser)表服务实现类
 *
 * @author Xujie
 * @since 2024/10/28 23:40
 */
@Slf4j
@Service
public class BizUserServiceImpl implements BizUserService {
  @Resource private BizUserMapper bizUserMapper;

  @Override
  public Long addOne(BizUser baseDO) {
    throw new CustomException("禁止调用");
  }

  @Override
  public BizUser getOneByEntity(BizUser baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizUser> getListByEntity(BizUser baseDO) {
    QueryWrapper<BizUser> bizUserQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizUserMapper.selectList(bizUserQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizUserMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizUser baseDO) {
    return bizUserMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizUser> getPageList(BizUser baseDO, Integer pageNum, Integer pageSize) {
    QueryWrapper<BizUser> bizUserQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizUserMapper.selectPage(new Page<>(pageNum, pageSize), bizUserQueryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizUserMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }

  @Override
  public List<BizUser> getUserListByUserIds(List<Long> ids) {
    if (ObjectUtils.isEmpty(ids)) {
      return Lists.newArrayList();
    }
    LambdaQueryWrapper<BizUser> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.in(BizUser::getId, ids);
    return bizUserMapper.selectList(queryWrapper);
  }
}
