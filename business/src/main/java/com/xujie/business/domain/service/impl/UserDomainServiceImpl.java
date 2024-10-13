package com.xujie.business.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.xujie.business.common.chain.register.RegisterChain;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.utils.SMSUtil;
import com.xujie.business.convert.UserConvert;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.domain.BO.BizUserSubscribeBO;
import com.xujie.business.domain.BO.BizVipBO;
import com.xujie.business.domain.service.CertificationDomainService;
import com.xujie.business.domain.service.UserDomainService;
import com.xujie.business.domain.service.UserSubscribeDomainService;
import com.xujie.business.domain.service.VipDomainService;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.service.UserService;
import com.xujie.tools.ConditionCheck;
import com.xujie.wx.entity.WxAppInfo;
import com.xujie.wx.utils.WxAppUtil;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 用户领域服务实现
 *
 * @author Xujie
 * @since 2024/10/5 15:02
 */
@Slf4j
@Service
public class UserDomainServiceImpl implements UserDomainService {
  @Resource private UserService userService;
  @Resource private VipDomainService vipDomainService;
  @Resource private CertificationDomainService certificationDomainService;
  @Resource private UserSubscribeDomainService userSubscribeDomainService;
  @Resource private SMSUtil smsUtil;
  @Resource private UserConvert userConvert;
  @Resource private WxAppUtil wxAppUtil;
  @Resource private RegisterChain registerChain;

  @Resource(name = "asyncExecutor")
  private ThreadPoolTaskExecutor asyncExecutor;

  /**
   * 通过手机号登录
   *
   * @param phone 用户手机号
   * @param code 验证码
   * @return 用户带Token信息
   */
  @Override
  public BizUserBO loginByPhone(String phone, String code) {
    BizUser userByEntity = userService.getUserByEntity(BizUser.builder().phone(phone).build());
    ConditionCheck.nullAndThrow(userByEntity, new CustomException("用户不存在"));
    smsUtil.checkCode(phone, code);
    StpUtil.login(userByEntity.getId());
    return setUserInfo(userByEntity);
  }

  /**
   * 通过微信登录
   *
   * @param code 微信code
   * @return 用户带Token信息
   */
  @Override
  public BizUserBO loginByWx(String code) {

    CompletableFuture<BizUserBO> userByEntityCompletableFuture =
        CompletableFuture.supplyAsync(
                () -> {
                  WxAppInfo wxAppInfo = null;
                  try {
                    wxAppInfo = wxAppUtil.getWxAppInfo(code);
                  } catch (Exception e) {
                    log.error("[用户登录][获取微信code]获取微信用户信息失败", e);
                    throw new CustomException("获取微信用户信息失败");
                  }
                  if (wxAppInfo.getErrcode() != null) {
                    throw new CustomException("获取微信用户信息失败");
                  }
                  BizUser userByEntity =
                      userService.getUserByEntity(
                          BizUser.builder().wxOpenId(wxAppInfo.getOpenid()).build());
                  ConditionCheck.nullAndThrow(userByEntity, new CustomException("用户不存在"));
                  return userByEntity;
                },
                asyncExecutor)
            .thenApply(this::setUserInfo);
    try {
      BizUserBO bizUserBO = userByEntityCompletableFuture.get(3, TimeUnit.SECONDS);
      StpUtil.login(bizUserBO.getId());
      return bizUserBO;
    } catch (ExecutionException e) {
      throw new CustomException(e.getMessage());
    } catch (InterruptedException e) {
      throw new CustomException("登录中断");
    } catch (TimeoutException e) {
      throw new CustomException("登录超时");
    }
  }

  @Override
  public Boolean sendCode(String phone) {
    BizUser userByEntity = userService.getUserByEntity(BizUser.builder().phone(phone).build());
    ConditionCheck.nullAndThrow(userByEntity, new CustomException("用户不存在"));
    String code = RandomUtil.randomNumbers(4);
    log.info("[用户发送验证码]{} == 验证码：{}", phone, code);
    try {
      smsUtil.sendCode(phone, code);
    } catch (CustomException e) {
      throw new CustomException(e.getMessage());
    } catch (RuntimeException e) {
      throw new CustomException("发送验证码失败");
    }
    return Boolean.TRUE;
  }

  @Override
  public BizUserBO register(BizUserBO userBo, String code) {
    BizUser userByEntity =
        userService.getUserByEntity(BizUser.builder().phone(userBo.getPhone()).build());
    ConditionCheck.trueAndThrow(userByEntity != null, new CustomException("手机号已经绑定用户"));
    WxAppInfo wxAppInfo = null;
    try {
      wxAppInfo = wxAppUtil.getWxAppInfo(code);
    } catch (Exception e) {
      log.error("[用户注册][获取微信code]获取微信用户信息失败", e);
      throw new CustomException("获取微信用户信息失败");
    }
    if (wxAppInfo.getErrcode() != null) {
      throw new CustomException("获取微信用户信息失败");
    }
    BizUser user = userConvert.convertBO2DO(userBo);
    user.setWxOpenId(wxAppInfo.getOpenid());
    try {
      userService.saveUser(user);
    } catch (Exception e) {
      throw new CustomException("注册失败，请联系管理员");
    }
    // 注册后操作
    try {
      registerChain.handle(user.getId());
    } catch (Exception e) {
      log.error("[用户注册][注册后操作]注册后操作失败", e);
    }
    StpUtil.login(user.getId());
    // 执行注册后的操作
    return setUserInfo(user);
  }

  @Override
  public BizUserBO getUserProfile(long loginIdAsLong) {
    return setUserInfo(userService.getUserByEntity(BizUser.builder().id(loginIdAsLong).build()));
  }

  @Override
  public Boolean updateUserInfo(Long loginId, BizUserBO userBO) {
    BizUser bizUser = userConvert.convertBO2DO(userBO);
    bizUser.setId(loginId);
    userService.updateUser(bizUser);
    return Boolean.TRUE;
  }

  @NotNull
  private BizUserBO setUserInfo(BizUser user) {
    BizUserBO bizUserBO = userConvert.convertDO2BO(user);

    try {
      // 查询用户VIP信息
      CompletableFuture<BizVipBO> vipByUserIdCompletableFuture =
          CompletableFuture.supplyAsync(
              () -> vipDomainService.getVipByUserId(user.getId()), asyncExecutor);
      CompletableFuture<BizCertificationBO> myCertificationCompletableFuture =
          CompletableFuture.supplyAsync(
              () -> certificationDomainService.getMyCertification(user.getId()), asyncExecutor);

      CompletableFuture<BizUserBO> bizUserBOCompletableFuture =
          vipByUserIdCompletableFuture.thenCombine(
              myCertificationCompletableFuture,
              (vipBO, certificationBO) -> {
                bizUserBO.setUserVip(vipBO);
                bizUserBO.setUserCertification(certificationBO);
                log.info("[用户登录][用户信息]{}", bizUserBO);
                return bizUserBO;
              });
      CompletableFuture<List<BizUserSubscribeBO>> listCompletableFuture =
          CompletableFuture.supplyAsync(
              () -> {
                List<BizUserSubscribeBO> subscribeList = new ArrayList<>();
                try {
                  subscribeList = userSubscribeDomainService.getSubscribeMyList(user.getId());
                } catch (Exception e) {
                  log.error("[用户登录][用户订阅]获取用户订阅信息失败", e);
                }
                return subscribeList;
              },
              asyncExecutor);
      CompletableFuture<BizUserBO> bizUserBOCompletableFuture1 =
          bizUserBOCompletableFuture.thenCombine(
              listCompletableFuture,
              (bizUserBO1, bizUserSubscribeBOS) -> {
                bizUserBO1.setSubscribeCount(bizUserSubscribeBOS.size());
                return bizUserBO1;
              });
      return bizUserBOCompletableFuture1.get(3, TimeUnit.SECONDS);
    } catch (Exception e) {
      e.printStackTrace();
      throw new CustomException("获取用户信息失败");
    }
  }
}
