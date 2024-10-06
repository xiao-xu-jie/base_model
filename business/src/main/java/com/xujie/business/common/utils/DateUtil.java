package com.xujie.business.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;

/**
 * 日期工具类
 *
 * @author Xujie
 * @since 2024/10/6 21:59
 */
public class DateUtil {
  /**
   * 获取今天的日期
   *
   * @return 获取今天的日期 yyyy-MM-dd Date类型
   */
  public static Date getToday() {
    // 创建一个 SimpleDateFormat 实例并指定日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // 获取当前日期
    Calendar calendar = Calendar.getInstance();

    // 将当前日期格式化为字符串
    String todayString = sdf.format(calendar.getTime());

    // 将格式化后的字符串解析为 Date 类型
    Date todayDate = null;
    try {
      todayDate = sdf.parse(todayString);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return todayDate;
  }

  public static String getTodayString() {
    // 将当前日期格式化为字符串
    return DateTime.now().toString("yyyy-MM-dd");
  }
}
