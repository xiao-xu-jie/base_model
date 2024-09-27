package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizGood;
import com.xujie.manager.infra.mapper.BizGoodMapper;
import com.xujie.manager.infra.service.BizGoodService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现
 *
 * @author Xujie
 * @since 2024/9/27 19:10
 */
@Slf4j
@Service
public class BizGoodServiceImpl implements BizGoodService {
  @Resource private BizGoodMapper baseMapper;

  @Override
  public Long addOne(BizGood baseDO) {
    int insert = baseMapper.insert(baseDO);
    return baseDO.getId();
  }

  @Override
  public BizGood getOneByEntity(BizGood baseDO) {

    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizGood> getListByEntity(BizGood baseDO) {
    return baseMapper.getByAll(baseDO);
  }

  @Override
  public boolean deleteOne(Long id) {
    return deleteBatch(new Long[] {id});
  }

  @Override
  public boolean updateOne(Long id, BizGood baseDO) {

    return baseMapper.updateById(baseDO, id) > 0;
  }

  @Override
  public Page<BizGood> getPageList(BizGood baseDO, Integer pageNum, Integer pageSize) {
    QueryWrapper<BizGood> query = Wrappers.query();
    query.eq(ObjectUtils.isNotEmpty(baseDO.getId()), BizGood.COL_id, baseDO.getId());
    query.like(
        StringUtils.isNotBlank(baseDO.getGoodName()), BizGood.COL_goodName, baseDO.getGoodName());
    query.orderByAsc(BizGood.COL_goodRank);
    query.eq(BizGood.COL_isDelete, 0);
    return baseMapper.getPageList(new Page<>(pageNum, pageSize), query);
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return baseMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
