package com.xujie.manager.domain.BO;

/**
 * (SysSystemLog)BO
 *
 * @author xujie
 * @since 2024-09-22 13:59:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLogBO extends BaseBO {


    /**
     * 日志ID
     */

    private Integer id;

    /**
     * 请求参数
     */

    private String req;

    /**
     * 返回参数
     */

    private String res;

    /**
     * 请求时间
     */

    private Date createTime;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 是否删除
     */

    private Integer isDelete;


}

