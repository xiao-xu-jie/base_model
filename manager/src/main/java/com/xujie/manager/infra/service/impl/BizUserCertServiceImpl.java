package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.utils.QueryWrapperUtil;
import com.xujie.manager.infra.DO.BizCertification;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.mapper.BizCertificationMapper;
import com.xujie.manager.infra.service.BizUserCertService;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 用户认证表(BizUserCert)表服务实现类
 *
 * @author Xujie
 * @since 2024/10/30 21:49
 */
@Slf4j
@Service
public class BizUserCertServiceImpl implements BizUserCertService {
  @Resource private BizCertificationMapper bizCertificationMapper;

  @Override
  public BizCertification getCertByUserId(Long userId) {
    LambdaQueryWrapper<BizCertification> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(BizCertification::getUserId, userId);
    return bizCertificationMapper.selectOne(queryWrapper);
  }

  @Override
  public List<BizCertification> getCertListByUserIds(List<Long> ids) {
    LambdaQueryWrapper<BizCertification> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.in(BizCertification::getUserId, ids);
    return bizCertificationMapper.selectList(queryWrapper);
  }

  @Override
  public Long addOne(BizCertification baseDO) {
    return bizCertificationMapper.insert(baseDO) > 0 ? baseDO.getId() : null;
  }

  @Override
  public BizCertification getOneByEntity(BizCertification baseDO) {
    return getListByEntity(baseDO).stream().findFirst().orElse(null);
  }

  @Override
  public List<BizCertification> getListByEntity(BizCertification baseDO) {
    QueryWrapper<BizCertification> bizCertificationQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    return bizCertificationMapper.selectList(bizCertificationQueryWrapper);
  }

  @Override
  public boolean deleteOne(Long id) {
    return bizCertificationMapper.deleteById(id) > 0;
  }

  @Override
  public boolean updateOne(Long id, BizCertification baseDO) {
    return bizCertificationMapper.updateById(baseDO) > 0;
  }

  @Override
  public Page<BizCertification> getPageList(
      BizCertification baseDO, Integer pageNum, Integer pageSize) {
    QueryWrapper<BizCertification> bizCertificationQueryWrapper =
        QueryWrapperUtil.buildQueryWrapper(baseDO);
    bizCertificationQueryWrapper.like(
        StringUtils.isNotBlank(baseDO.getNickName()),
        "biz_user." + BizUser.COL_NICK_NAME,
        baseDO.getNickName());
    return bizCertificationMapper.getPageList(
        bizCertificationQueryWrapper, new Page<>(pageNum, pageSize));
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return bizCertificationMapper.deleteByIds(Arrays.asList(ids)) > 0;
  }
}
