package com.xujie.manager.infra.service;

import com.xujie.manager.common.base.service.BaseService;
import com.xujie.manager.infra.DO.BizUserVip;
import java.util.List;

public interface BizUserVipService extends BaseService<BizUserVip> {
  List<BizUserVip> getListByUserIds(List<Long> userIds);

  void deleteUserVip(Long id);
}
