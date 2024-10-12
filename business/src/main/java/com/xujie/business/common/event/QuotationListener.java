package com.xujie.business.common.event;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.business.common.constants.SMSConstant;
import com.xujie.business.common.utils.SMSUtil;
import com.xujie.business.domain.BO.BizEggQuotationBO;
import com.xujie.business.infra.DO.BizUser;
import com.xujie.business.infra.DO.BizUserSubscribe;
import com.xujie.business.infra.DO.BizUserVip;
import com.xujie.business.infra.mapper.BizUserVipMapper;
import com.xujie.business.infra.service.UserService;
import com.xujie.business.infra.service.UserSubscribeService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xujie
 * @since 2024/10/12 21:02
 */
@Slf4j
@Component
public class QuotationListener implements ApplicationListener<QuotationSubmitEvent> {
  @Resource private UserSubscribeService userSubscribeService;
  @Resource private BizUserVipMapper bizUserVipMapper;
  @Resource private UserService userService;
  @Resource private SMSUtil smsUtil;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void onApplicationEvent(QuotationSubmitEvent event) {
    log.info("[用户订阅发布短信]：收到报价事件：{}", event);
    List<BizUserSubscribe> subscribedUsers =
        userSubscribeService.getSubscribedUsers(event.getSubUserId());
    if (subscribedUsers.isEmpty()) {
      log.info("[用户订阅发布短信]：无订阅用户");
      return;
    } else {
      log.info("[用户订阅发布短信]：订阅用户：{}", subscribedUsers);
      // 查询所有订阅该用户的手机号
      List<Long> ids =
          subscribedUsers.stream().map(BizUserSubscribe::getUserId).filter(this::isEnough).toList();
      String[] phones =
          userService.getUserListByIds(ids).stream().map(BizUser::getPhone).toArray(String[]::new);

      if (ObjectUtils.isNotEmpty(phones)) {
        String phoneString = String.join(",", phones);
        log.info("[用户订阅发布短信]：订阅用户手机号：{}", phoneString);
        // 发送短信
        Long userId = event.getBizEggQuotationBO().getUserId();
        BizEggQuotationBO bizEggQuotationBO = event.getBizEggQuotationBO();
        BizUser userByEntity = userService.getUserByEntity(BizUser.builder().id(userId).build());
        String message =
            String.format(
                SMSConstant.SMS_NOTIFY_CONTENT,
                userByEntity.getNickName(),
                "类型："
                    + bizEggQuotationBO.getEggTypeName()
                    + ", 发布地区："
                    + bizEggQuotationBO.getQuotationLocation()
                    + ", 参考价格："
                    + bizEggQuotationBO.getQuotationAvgPrice());
        smsUtil.sendNotify(phoneString, message);
      } else {
        log.info("[用户订阅发布短信]：无订阅用户手机号");
      }
    }
  }

  public Boolean isEnough(Long userId) {
    List<BizUserVip> bizUserVips =
        bizUserVipMapper.selectList(
            Wrappers.<BizUserVip>lambdaQuery().eq(BizUserVip::getUserId, userId));
    Optional<BizUserVip> first = bizUserVips.stream().findFirst();
    AtomicReference<Boolean> isEnough = new AtomicReference<>(false);
    first.ifPresent(
        item -> {
          if (item.getNotifyCount() > 0) {
            isEnough.set(item.getNotifyCount() > 0);
            item.setNotifyCount(item.getNotifyCount() - 1);
            BizUserVip build = BizUserVip.builder().notifyCount(item.getNotifyCount()).build();
            bizUserVipMapper.updateByUserId(build, item.getUserId());
            log.info("[用户订阅发布短信]：用户：{}，剩余短信次数：{}", userId, item.getNotifyCount());
          } else {
            isEnough.set(false);
            log.info("[用户订阅发布短信]：用户：{}，短信次数不足", userId);
            if (item.getNotifyCount() == 0) {
              item.setNotifyCount(-1);
              BizUserVip build = BizUserVip.builder().notifyCount(item.getNotifyCount()).build();

              bizUserVipMapper.updateByUserId(build, item.getUserId());

              // 发布余额不足通知
              BizUser userByEntity =
                  userService.getUserByEntity(BizUser.builder().id(userId).build());

              smsUtil.sendNotify(userByEntity.getPhone(), SMSConstant.SMS_COUNT_NOT_ENOUGH);
            }
          }
        });
    return isEnough.get();
  }
}
