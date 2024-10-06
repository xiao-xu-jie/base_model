package com.xujie.business.common.utils;

import cn.hutool.json.JSONObject;
import com.xujie.business.application.redis.utils.RedisUtils;
import com.xujie.business.common.constants.SMSConstant;
import com.xujie.business.common.exception.CustomException;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 短信验证码工具类
 *
 * @author Xujie
 * @since 2024/10/5 15:28
 */
@Component
public class SMSUtil {
  @Resource private WebClient webClient;
  private ReentrantLock lock = new ReentrantLock();

  /**
   * 发送短信验证码
   *
   * @param phone 手机号
   * @param code 验证码
   */
  public void sendCode(String phone, String code) {
    // 限制判断
    lock.lock();
    try {
      int count = RedisUtils.<Integer>getCacheObject(SMSConstant.SMS_REDIS_COUNT_KEY).orElse(0);
      if (count >= 1) {
        throw new CustomException("发送次数过多，请稍后再试");
      }
      RedisUtils.setCacheObject(SMSConstant.SMS_REDIS_COUNT_KEY, count + 1, 1, TimeUnit.MINUTES);
    } finally {
      lock.unlock();
    }

    // 发送短信验证码
    String url = String.format(SMSConstant.SMS_CODE_API, phone, code);

    String res =
        webClient
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono(String.class)
            .block(Duration.of(3, ChronoUnit.SECONDS));
    JSONObject jsonObject = new JSONObject(res);
    if ("0".equals(jsonObject.getStr("code"))) {
      throw new CustomException("短信发送失败");
    }
    // 验证码缓存
    RedisUtils.setCacheObject(SMSConstant.SMS_REDIS_KEY + phone, code, 60, TimeUnit.SECONDS);
  }

  /**
   * 校验短信验证码
   *
   * @param phone 手机号
   * @param code 验证码
   */
  public void checkCode(String phone, String code) {
    String cacheCode =
        RedisUtils.<String>getCacheObject(SMSConstant.SMS_REDIS_KEY + phone)
            .orElseThrow(() -> new CustomException("验证码已过期"));
    if (!code.equals(cacheCode)) {
      throw new CustomException("验证码错误");
    }
  }
}
