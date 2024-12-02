package com.xujie.business.common.templates.twoNinePlatform.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 29平台提交订单请求体
 *
 * @author Xujie
 * @since 2024/11/15 12:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitOrderRequest {
  /** 请求地址 */
  private String url;

  /** 平台账号UID */
  private String uid;

  /** 平台账号KEY */
  private String key;

  /** 对接平台ID */
  private Integer platform;

  /** 用户账号（手机号） */
  private String user;

  private String school = "无";

  /** 用户密码 */
  private String pass;

  /** 课程名称 */
  private String kcname;

  /** 课程ID */
  private String kcid;
}
