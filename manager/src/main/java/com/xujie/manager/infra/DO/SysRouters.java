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
@TableName(value = "sys_routers")
public class SysRouters {
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_ICON = "icon";
    public static final String COL_TITLE = "title";
    public static final String COL_PARENT_ID = "parent_id";
    public static final String COL_PATH = "path";
    public static final String COL_REDIRECT = "redirect";
    public static final String COL_COMPONENT = "component";
    public static final String COL_SHOWLINK = "showLink";
    public static final String COL_RANK = "rank";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_IS_DELETE = "is_delete";
    /**
     * 路由ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;
    /**
     * icon
     */
    @TableField(value = "icon")
    private String icon;
    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;
    /**
     * 父亲节点
     */
    @TableField(value = "parent_id")
    private Long parentId;
    /**
     * 路径
     */
    @TableField(value = "`path`")
    private String path;
    /**
     * 重定向
     */
    @TableField(value = "redirect")
    private String redirect;
    /**
     * 组件
     */
    @TableField(value = "component")
    private String component;
    /**
     * 是否显示
     */
    @TableField(value = "showLink")
    private Boolean showlink;
    /**
     * 排序
     */
    @TableField(value = "`rank`")
    private Integer rank;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)

    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;
}