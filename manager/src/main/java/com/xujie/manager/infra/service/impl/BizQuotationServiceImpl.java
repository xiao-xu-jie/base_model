package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizEggQuotation;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.mapper.BizEggQuotationMapper;
import com.xujie.manager.infra.service.BizQuotationService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * (BizQuotation)表服务实现类
 *
 * @author Xujie
 * @since 2024/10/31 10:51
 */
@Slf4j
@Service
public class BizQuotationServiceImpl implements BizQuotationService {
  @Resource private BizEggQuotationMapper bizEggQuotationMapper;

  @Override
  public List<BizEggQuotation> getQuotationListByUserIds(List<Long> userIds) {
    LambdaQueryWrapper<BizEggQuotation> lambdaQuery = new LambdaQueryWrapper<>();
    lambdaQuery.orderByDesc(BizEggQuotation::getDataDate);
    lambdaQuery.in(BizEggQuotation::getUserId, userIds);
    return bizEggQuotationMapper.selectList(lambdaQuery);
  }

  @Override
  public Long addOne(BizEggQuotation baseDO) {
    return bizEggQuotationMapper.insert(baseDO) > 0 ? baseDO.getId() : 0;
  }

  @Override
  public BizEggQuotation getOneByEntity(BizEggQuotation baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizEggQuotation> getListByEntity(BizEggQuotation baseDO) {
    QueryWrapper<BizEggQuotation> bizEggQuotationQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizEggQuotationMapper.selectList(bizEggQuotationQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizEggQuotationMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizEggQuotation baseDO) {
    return bizEggQuotationMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizEggQuotation> getPageList(
      BizEggQuotation baseDO, Integer pageNum, Integer pageSize) {
    QueryWrapper<BizEggQuotation> bizEggQuotationQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    bizEggQuotationQueryWrapper.like(
        StringUtils.isNotBlank(baseDO.getNickName()),
        "biz_user." + BizUser.COL_NICK_NAME,
        baseDO.getNickName());
    bizEggQuotationQueryWrapper.orderByDesc(BizEggQuotation.COL_DATA_DATE);
    return bizEggQuotationMapper.getPageList(
        bizEggQuotationQueryWrapper, new Page<>(pageNum, pageSize));
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizEggQuotationMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
