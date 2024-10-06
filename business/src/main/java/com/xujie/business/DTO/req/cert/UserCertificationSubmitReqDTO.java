package com.xujie.business.DTO.req.cert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户认证信息提交DTO
 *
 * @author Xujie
 * @since 2024/10/6 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCertificationSubmitReqDTO {
  /** 真实名称 */
  @NotBlank(message = "真实名称不能为空")
  @Size(min = 2, max = 10, message = "真实名称长度必须在2-10之间")
  private String realName;

  /** 身份证号 */
  @NotBlank(message = "身份证号不能为空")
  @Pattern(regexp = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", message = "身份证号格式不正确")
  private String idCardNo;

  /** 手机号 */
  @NotBlank(message = "手机号不能为空")
  @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
  private String phoneNumber;

  /** 认证类型 */
  private String certType;

  /** 其余资料 */
  private String imgs;
}
