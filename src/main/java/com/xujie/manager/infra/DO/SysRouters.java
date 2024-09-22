package com.xujie.manager.infra.DO;

/**
 * (SysRouters)表实体类
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@Data
@TableName(value = "sys_routers")
public class SysRouters extends BaseDO {

    public static final String COL_id = "id";
    public static final String COL_name = "name";
    public static final String COL_icon = "icon";
    public static final String COL_title = "title";
    public static final String COL_parentId = "parent_id";
    public static final String COL_path = "path";
    public static final String COL_redirect = "redirect";
    public static final String COL_component = "component";
    public static final String COL_showlink = "showLink";
    public static final String COL_rank = "rank";
    public static final String COL_createTime = "create_time";
    public static final String COL_updateTime = "update_time";
    public static final String COL_isDelete = "is_delete";


    /**
     * 路由ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
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
    @TableField(value = "path")
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
    @TableField(value = "rank")
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
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;


}

