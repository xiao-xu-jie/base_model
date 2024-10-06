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
}
