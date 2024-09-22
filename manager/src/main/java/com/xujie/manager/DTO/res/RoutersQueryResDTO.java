package com.xujie.manager.DTO.res;


import com.xujie.manager.common.base.model.BaseDTO;
import com.xujie.manager.domain.BO.RoutersBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * (SysRouters)查询返回DTO
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersQueryResDTO extends BaseDTO {


    /**
     * 路由ID
     */

    private Long id;

    /**
     * 名称
     */

    private String name;

    /**
     * icon
     */

    private String icon;

    /**
     * 标题
     */

    private String title;

    /**
     * 父亲节点
     */

    private Long parentId;

    /**
     * 路径
     */

    private String path;

    /**
     * 重定向
     */

    private String redirect;

    /**
     * 组件
     */

    private String component;

    /**
     * 是否显示
     */

    private Boolean showlink;

    /**
     * 排序
     */

    private Integer rank;

    /**
     * 创建时间
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

    List<RoutersBO> children;

    Boolean hasChildren;

}

