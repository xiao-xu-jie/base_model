package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.manager.infra.DO.BizCertification;
import com.xujie.manager.infra.mapper.BizCertificationMapper;
import com.xujie.manager.infra.service.BizUserCertService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
}
