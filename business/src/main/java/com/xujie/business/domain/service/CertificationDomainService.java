package com.xujie.business.domain.service;

import com.xujie.business.domain.BO.BizCertificationBO;

public interface CertificationDomainService {
  /**
   * 通过实体获取认证信息
   *
   * @param userId 用户id
   * @return 认证信息
   */
  BizCertificationBO getMyCertification(Long userId);

  /**
   * 插入认证信息
   *
   * @param certificationBO 认证信息
   */
  void submit(BizCertificationBO certificationBO);
}
