package com.xujie.business.DTO.res.user;

import com.xujie.business.DTO.res.cert.UserCertificationResDTO;
import com.xujie.business.DTO.res.vip.VipUserResDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录响应DTO
 *
 * @author Xujie
 * @since 2024/10/5 14:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResDTO {
  /** 用户ID */
  private Long id;

  /** 用户手机号 */
  private String phone;

  /** 微信openid */
  private String wxOpenId;

  /** 用户名 */
  private String nickName;

  /** 头像 */
  private String userAvatar;

  /** 用户介绍 */
  private String userDesc;

  /** 用户位置 */
  private String userLocation;

  /** 用户VIP */
  private VipUserResDTO userVip;

  /** 订阅数 */
  private Integer subscribeCount;

  /** 用户认证信息 */
  private UserCertificationResDTO userCertification;

  /** Token */
  private String token;
}
