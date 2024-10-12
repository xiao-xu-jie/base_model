package com.xujie.business.common.constants;

/**
 * 描述: 短信常量
 *
 * @author Xujie
 * @since 2024/10/5 15:30
 */
public class SMSConstant {
  public static final String SMS_CODE_API =
      "https://sms.zovps.com/sendApi?channel=Channel_2024092418642&username=3285503539&key=6fa888aca6997246f70ca4a761a37cd3&phone=%s&content=【小徐科技】你的验证码是%s，短信验证码60s内有效，请勿泄露。";
  public static final String SMS_REDIS_KEY = "sms_code:";
  public static final String SMS_REDIS_COUNT_KEY = "sms_code:count";
  public static final String SMS_NOTIFY_API =
      "https://sms.zovps.com/sendApi?channel=Channel_2024092418642&username=3285503539&key=6fa888aca6997246f70ca4a761a37cd3&phone=%s&content=%s";
  public static final String SMS_NOTIFY_CONTENT =
      "【蜀蓝科技】尊贵的用户您好，您订阅的用户【%s】发布了新的报价内容，%s，快来蛋价八点半小程序查看吧！";
  public static final String SMS_COUNT_NOT_ENOUGH = "【蜀蓝科技】尊贵的用户您好，您的通知余额不足，后续订阅用户将不会同步发信息！";
}
