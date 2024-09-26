package com.xujie.business.infra.DO;

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
@TableName(value = "sys_oper_log")
public class SysOperLog {
    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 日志描述
     */
    @TableField(value = "log_desc")
    private String logDesc;

    /**
     * 执行方法名称
     */
    @TableField(value = "method_name")
    private String methodName;

    /**
     * 请求参数
     */
    @TableField(value = "request_params")
    private String requestParams;

    /**
     * 请求方法
     */
    @TableField(value = "request_type")
    private String requestType;

    /**
     * 返回体
     */
    @TableField(value = "response_body")
    private String responseBody;

    /**
     * 请求地址
     */
    @TableField(value = "request_path")
    private String requestPath;

    /**
     * 错误信息
     */
    @TableField(value = "error_log")
    private String errorLog;

    /**
     * 操作人
     */
    @TableField(value = "operate_user")
    private Long operateUser;

    /**
     * 操作IP
     */
    @TableField(value = "operate_ip")
    private String operateIp;

    /**
     * 通知用户
     */
    @TableField(value = "is_notify")
    private String isNotify;

    /**
     * 消耗毫秒
     */
    @TableField(value = "cost_time")
    private Long costTime;

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

    public static final String COL_ID = "id";

    public static final String COL_LOG_DESC = "log_desc";

    public static final String COL_METHOD_NAME = "method_name";

    public static final String COL_REQUEST_PARAMS = "request_params";

    public static final String COL_REQUEST_TYPE = "request_type";

    public static final String COL_RESPONSE_BODY = "response_body";

    public static final String COL_REQUEST_PATH = "request_path";

    public static final String COL_ERROR_LOG = "error_log";

    public static final String COL_OPERATE_USER = "operate_user";

    public static final String COL_OPERATE_IP = "operate_ip";

    public static final String COL_IS_NOTIFY = "is_notify";

    public static final String COL_COST_TIME = "cost_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IS_DELETE = "is_delete";
}