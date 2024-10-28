package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (BizUser)BO
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizUserBO extends BaseBO {

  /** 用户ID */
  private Long id;

  /** 用户手机号 */
  private String phone;

  /** 微信openid */
  private String wxOpenId;

  /** 微信公共id */
  private String wxUnionId;

  /** 用户名 */
  private String nickName;

  /** 头像 */
  private String userAvatar;

  /** 用户介绍 */
  private String userDesc;

  /** 用户位置 */
  private String userLocation;

  /** 用户状态-0,1 */
  private Integer userStatus;

  private Date createTime;

  private Date updateTime;

  private Integer isDelete;
}
