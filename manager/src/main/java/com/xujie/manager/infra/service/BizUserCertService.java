package com.xujie.manager.infra.service;

import com.xujie.manager.infra.DO.BizCertification;
import java.util.List;

public interface BizUserCertService {
  BizCertification getCertByUserId(Long userId);

  List<BizCertification> getCertListByUserIds(List<Long> ids);
}
