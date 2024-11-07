package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizSlider;
import com.xujie.manager.infra.mapper.BizSliderMapper;
import com.xujie.manager.infra.service.BizSliderService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发布页面轮播(BizSlider)表服务实现类
 *
 * @author Xujie
 * @since 2024/11/7 09:11
 */
@Slf4j
@Service
public class BizSliderServiceImpl implements BizSliderService {
  @Resource private BizSliderMapper bizSliderMapper;

  @Override
  public Long addOne(BizSlider baseDO) {
    return bizSliderMapper.insert(baseDO) > 0 ? baseDO.getId() : 0;
  }

  @Override
  public BizSlider getOneByEntity(BizSlider baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizSlider> getListByEntity(BizSlider baseDO) {
    QueryWrapper<BizSlider> bizSliderQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizSliderMapper.selectList(bizSliderQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizSliderMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizSlider baseDO) {
    return bizSliderMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizSlider> getPageList(BizSlider baseDO, Integer pageNum, Integer pageSize) {
    Page<BizSlider> page = new Page<>(pageNum, pageSize);
    QueryWrapper<BizSlider> bizSliderQueryWrapper = QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizSliderMapper.selectPage(page, bizSliderQueryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizSliderMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
