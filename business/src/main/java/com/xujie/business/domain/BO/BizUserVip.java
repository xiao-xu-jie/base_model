package com.xujie.business.domain.BO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizUserVip {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 会员ID
    */
    private Long vipId;

    /**
    * 会员名称
    */
    private String vipName;

    /**
    * 到期时间
    */
    private Date expireTime;

    /**
    * 是否到期
    */
    private Integer isExpire;

    /**
    * 发布次数
    */
    private Integer postCount;

    /**
    * 通知次数
    */
    private Integer notifyCount;
}