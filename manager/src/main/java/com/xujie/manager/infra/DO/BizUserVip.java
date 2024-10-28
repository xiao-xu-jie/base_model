package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_user_vip")
public class BizUserVip {
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 会员ID
     */
    @TableField(value = "vip_id")
    private Long vipId;

    /**
     * 会员名称
     */
    @TableField(value = "vip_name")
    private String vipName;

    /**
     * 到期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

    /**
     * 是否到期
     */
    @TableField(value = "is_expire")
    private Integer isExpire;

    /**
     * 发布次数
     */
    @TableField(value = "post_count")
    private Integer postCount;

    /**
     * 通知次数
     */
    @TableField(value = "notify_count")
    private Integer notifyCount;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_VIP_ID = "vip_id";

    public static final String COL_VIP_NAME = "vip_name";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_IS_EXPIRE = "is_expire";

    public static final String COL_POST_COUNT = "post_count";

    public static final String COL_NOTIFY_COUNT = "notify_count";
}