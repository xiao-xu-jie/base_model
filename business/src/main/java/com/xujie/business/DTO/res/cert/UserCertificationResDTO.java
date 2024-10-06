package com.xujie.business.DTO.res.cert;

import com.xujie.business.common.enums.CertificationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户认证信息返回DTO
 *
 * @author Xujie
 * @since 2024/10/6 11:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCertificationResDTO {
  /** 认证ID */
  private Long id;

  /** 真实名称 */
  private String realName;

  /** 身份证号 */
  private String idCardNo;

  /** 手机号 */
  private String phoneNumber;

  /** 认证类型 */
  private String certType;

  /** 其余资料 */
  private String imgs;

  /** 认证状态 */
  private CertificationStatusEnum certStatus;
}
