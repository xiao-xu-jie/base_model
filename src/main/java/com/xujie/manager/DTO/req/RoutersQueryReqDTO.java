package com.xujie.manager.DTO.req;

/**
 * (SysRouters)查询请求DTO
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersQueryReqDTO extends BaseDTO {


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


}

