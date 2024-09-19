package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser {
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";
    public static final String COL_SEX = "sex";
    public static final String COL_EMAIL = "email";
    public static final String COL_OPEN_ID = "open_id";
    public static final String COL_PHONE = "phone";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_IS_DELETE = "is_delete";
    public static final String COL_VERSION = "version";
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;
    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;
    /**
     * 性别
     */
    @TableField(value = "sex")
    private Integer sex;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
    /**
     * openId
     */
    @TableField(value = "open_id")
    private Integer openId;
    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;
    /**
     * 版本号
     */
    @TableField(value = "version")
    private Long version;
}