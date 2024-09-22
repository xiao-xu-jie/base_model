package com.xujie.manager.infra.DO;

/**
 * (SysSystemLog)表实体类
 *
 * @author xujie
 * @since 2024-09-22 13:59:55
 */
@Data
@TableName(value = "sys_system_log")
public class SysSystemLog extends BaseDO {

    public static final String COL_id = "id";
    public static final String COL_req = "req";
    public static final String COL_res = "res";
    public static final String COL_createTime = "create_time";
    public static final String COL_updateTime = "update_time";
    public static final String COL_isDelete = "is_delete";


    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请求参数
     */
    @TableField(value = "req")
    private String req;

    /**
     * 返回参数
     */
    @TableField(value = "res")
    private String res;

    /**
     * 请求时间
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

