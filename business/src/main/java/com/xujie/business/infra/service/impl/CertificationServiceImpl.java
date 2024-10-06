package com.xujie.business.infra.service.impl;

import com.xujie.business.infra.DO.BizCertification;
import com.xujie.business.infra.mapper.BizCertificationMapper;
import com.xujie.business.infra.service.CertificationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 认证服务实现
 *
 * @author Xujie
 * @since 2024/10/6 11:19
 */
@Slf4j
@Service
public class CertificationServiceImpl implements CertificationService {
  @Resource private BizCertificationMapper certificationMapper;

  /**
   * 通过实体获取认证信息
   *
   * @param certification 认证实体
   * @return 认证信息
   */
  @Override
  public BizCertification getCertificationByEntity(BizCertification certification) {
    return certificationMapper.getByAll(certification).stream().findFirst().orElse(null);
  }

  @Override
  public void insertCertification(BizCertification certification) {
    certificationMapper.insert(certification);
  }
}
