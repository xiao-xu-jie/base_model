package com.xujie.manager.domain.BO;

import com.xujie.manager.common.base.model.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (SysSystemLog)BO
 *
 * @author xujie
 * @since 2024-09-22 10:18:05
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

