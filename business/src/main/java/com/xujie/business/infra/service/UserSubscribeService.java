package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizUserSubscribe;
import java.util.List;

public interface UserSubscribeService {
  void addSubscribe(Long userId, Long subUserId);

  void cancelSubscribe(Long userId, Long subUserId);

  Boolean isSubscribe(Long userId, Long subUserId);

  List<BizUserSubscribe> getSubscribedUsers(Long userId);

  List<BizUserSubscribe> getSubscribeMyUsers(Long userId);
}
