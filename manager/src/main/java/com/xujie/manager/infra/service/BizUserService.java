package com.xujie.manager.infra.service;

import com.xujie.manager.common.base.service.BaseService;
import com.xujie.manager.infra.DO.BizUser;
import java.util.List;

public interface BizUserService extends BaseService<BizUser> {
  List<BizUser> getUserListByUserIds(List<Long> ids);
}
