package com.xujie.manager.infra.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizOrder;
import com.xujie.manager.infra.mapper.BizOrderMapper;
import com.xujie.manager.infra.service.BizOrderService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现
 *
 * @author Xujie
 * @since 2024/9/27 19:11
 */
@Slf4j
@Service
public class BizOrderServiceImpl implements BizOrderService {
  @Resource private BizOrderMapper baseMapper;

  @Override
  public Long addOne(BizOrder baseDO) {
    return baseMapper.insert(baseDO) > 0 ? baseDO.getId() : null;
  }

  @Override
  public BizOrder getOneByEntity(BizOrder baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizOrder> getListByEntity(BizOrder baseDO) {
    return baseMapper.getByAll(baseDO);
  }

  @Override
  public boolean deleteOne(Long id) {
    return deleteBatch(new Long[] {id});
  }

  @Override
  public boolean updateOne(Long id, BizOrder baseDO) {
    return baseMapper.updateById(baseDO, id) > 0;
  }

  @Override
  public Page<BizOrder> getPageList(BizOrder baseDO, Integer pageNum, Integer pageSize) {
    LambdaQueryWrapper<BizOrder> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(ObjectUtils.isNotEmpty(baseDO.getId()), BizOrder::getId, baseDO.getId());
    queryWrapper.eq(
        ObjectUtils.isNotEmpty(baseDO.getOrderNo()), BizOrder::getOrderNo, baseDO.getOrderNo());
    queryWrapper.like(
        StringUtils.isNotBlank(baseDO.getGoodName()), BizOrder::getGoodName, baseDO.getGoodName());
    if (ObjectUtils.isNotEmpty(baseDO.getCreateTime())) {
      queryWrapper.ge(BizOrder::getCreateTime, DateUtil.beginOfDay(baseDO.getCreateTime()));
      queryWrapper.le(BizOrder::getCreateTime, DateUtil.endOfDay(baseDO.getCreateTime()));
    }

    queryWrapper.eq(
        StringUtils.isNotBlank(baseDO.getPhone()), BizOrder::getPhone, baseDO.getPhone());
    queryWrapper.orderByDesc(BizOrder::getPayTime, BizOrder::getCreateTime);
    return baseMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    System.out.println("deleteBatch");
    return baseMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
