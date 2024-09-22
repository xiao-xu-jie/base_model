package com.xujie.manager.DTO.res;


import com.xujie.manager.common.base.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (SysSystemLog)查询返回DTO
 *
 * @author xujie
 * @since 2024-09-22 10:18:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLogQueryResDTO extends BaseDTO {


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

