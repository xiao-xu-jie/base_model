package com.xujie.manager.infra.service;

import com.xujie.manager.common.base.service.BaseService;
import com.xujie.manager.infra.DO.BizCertification;
import java.util.List;

public interface BizUserCertService extends BaseService<BizCertification> {
  BizCertification getCertByUserId(Long userId);

  List<BizCertification> getCertListByUserIds(List<Long> ids);
}
