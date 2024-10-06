package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizUser;

public interface UserService {
  /**
   * 通过实体获取用户
   *
   * @param user 用户实体
   * @return
   */
  BizUser getUserByEntity(BizUser user);

  void saveUser(BizUser user);
}
