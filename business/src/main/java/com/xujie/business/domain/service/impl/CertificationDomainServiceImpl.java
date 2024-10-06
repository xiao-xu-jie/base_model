package com.xujie.business.domain.service.impl;

import com.xujie.business.common.exception.CustomException;
import com.xujie.business.convert.CertificationConvert;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.domain.service.CertificationDomainService;
import com.xujie.business.infra.DO.BizCertification;
import com.xujie.business.infra.service.CertificationService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 认证领域服务实现
 *
 * @author Xujie
 * @since 2024/10/6 11:23
 */
@Slf4j
@Service
public class CertificationDomainServiceImpl implements CertificationDomainService {
  @Resource private CertificationService certificationService;
  @Resource private CertificationConvert certificationConvert;

  /**
   * 通过实体获取认证信息
   *
   * @param userId 用户id
   * @return 认证信息
   */
  @Override
  public BizCertificationBO getMyCertification(Long userId) {
    BizCertification certificationByEntity =
        certificationService.getCertificationByEntity(
            BizCertification.builder().userId(userId).build());
    return certificationConvert.convertDO2BO(certificationByEntity);
  }

  /**
   * 插入认证信息
   *
   * @param certificationBO 认证信息
   */
  @Override
  public void submit(BizCertificationBO certificationBO) {
    BizCertification certificationByEntity =
        certificationService.getCertificationByEntity(
            BizCertification.builder().userId(certificationBO.getUserId()).build());
    ConditionCheck.trueAndThrow(certificationByEntity != null, new CustomException("已经提交过认证信息了"));
    certificationService.insertCertification(certificationConvert.convertBO2DO(certificationBO));
  }
}
