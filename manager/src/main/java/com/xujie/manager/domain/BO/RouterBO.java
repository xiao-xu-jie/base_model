package com.xujie.manager.domain.BO;

import com.xujie.manager.DTO.res.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/17 22:30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouterBO {
    /**
     * 子节点
     */
    List<RouterBO> children;
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
     * meta
     */
    private Meta meta;

}
