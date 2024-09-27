package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizSourceStation;
import com.xujie.manager.infra.mapper.BizSourceStationMapper;
import com.xujie.manager.infra.service.BizSourceService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 来源服务实现
 *
 * @author Xujie
 * @since 2024/9/27 19:11
 */
@Slf4j
@Service
public class BizSourceServiceImpl implements BizSourceService {
  @Resource private BizSourceStationMapper baseMapper;

  @Override
  public Long addOne(BizSourceStation baseDO) {
    return baseMapper.insert(baseDO) > 0 ? baseDO.getId() : 0;
  }

  @Override
  public BizSourceStation getOneByEntity(BizSourceStation baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizSourceStation> getListByEntity(BizSourceStation baseDO) {
    return baseMapper.getByAll(baseDO);
  }

  @Override
  public boolean deleteOne(Long id) {
    return baseMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizSourceStation baseDO) {
    return baseMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizSourceStation> getPageList(
      BizSourceStation baseDO, Integer pageNum, Integer pageSize) {
    LambdaQueryWrapper<BizSourceStation> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.like(
        StringUtils.isNotBlank(baseDO.getStationName()),
        BizSourceStation::getStationName,
        baseDO.getStationName());
    queryWrapper.eq(
        ObjectUtils.isNotEmpty(baseDO.getStationStatus()),
        BizSourceStation::getStationStatus,
        baseDO.getStationStatus());
    return baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return baseMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
