package com.xujie.business.infra.service.impl;

import com.xujie.business.common.enums.UserStatusEnum;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.mapper.BizUserMapper;
import com.xujie.business.infra.service.UserService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * 用户基础服务实现
 *
 * @author Xujie
 * @since 2024/10/5 15:06
 */
@Service
public class UserServiceImpl implements UserService {
  @Resource private BizUserMapper userMapper;

  /**
   * 通过实体获取用户
   *
   * @param user 用户实体
   * @return 用户
   */
  @Override
  public BizUser getUserByEntity(BizUser user) {
    Optional<BizUser> userOptional = userMapper.getByAll(user).stream().findAny();
    userOptional.ifPresent(
        item -> {
          ConditionCheck.trueAndThrow(
              UserStatusEnum.DISABLE.equals(item.getUserStatus()), new CustomException("用户已被禁用"));
        });
    return userOptional.orElse(null);
  }

  @Override
  public void saveUser(BizUser user) {
    userMapper.insert(user);
  }
}
