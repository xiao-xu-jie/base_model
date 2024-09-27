package com.xujie.business.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 提交订单返回DTO
 *
 * @author Xujie
 * @since 2024/9/27 17:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitOrderResDTO {
  private Integer code;
  private String msg;
  private Integer status;
  private String message;
  private String id;
}
