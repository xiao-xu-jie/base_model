package com.xujie.manager.DTO.res;

import com.xujie.manager.common.base.model.BaseDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizCertification)查询返回DTO
 *
 * @author xujie
 * @since 2024-10-31 11:46:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizCertificationQueryResDTO extends BaseDTO {

  /** 认证ID */
  private Long id;

  /** 用户ID */
  private Long userId;

  /** 昵称 */
  private String nickName;

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
  private Integer certStatus;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
