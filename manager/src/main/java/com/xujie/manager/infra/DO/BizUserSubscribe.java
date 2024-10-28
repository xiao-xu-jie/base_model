package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_user_subscribe")
public class BizUserSubscribe {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 订阅的用户ID
     */
    @TableField(value = "sub_user_id")
    private Long subUserId;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SUB_USER_ID = "sub_user_id";
}