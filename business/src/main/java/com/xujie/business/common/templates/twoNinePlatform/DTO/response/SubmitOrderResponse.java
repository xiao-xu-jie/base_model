package com.xujie.business.common.templates.twoNinePlatform.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 29平台提交订单返回响应
 *
 * @author Xujie
 * @since 2024/11/15 12:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitOrderResponse {
  /** 状态码 0：成功 1：失败 */
  private Integer code;

  /** 返回信息 */
  private String msg;
}
