package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizCertification;

public interface CertificationService {
  /**
   * 通过实体获取认证信息
   *
   * @param certification 认证实体
   * @return 认证信息
   */
  BizCertification getCertificationByEntity(BizCertification certification);

  /**
   * 插入认证信息
   *
   * @param certification 认证信息
   */
  void insertCertification(BizCertification certification);
}
