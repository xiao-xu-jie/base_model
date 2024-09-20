package com.xujie.manager.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xujie
 * @since 2024/9/20 21:32
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleBO {
    /**
     * 角色Id
     */

    private Long id;

    /**
     * 角色code
     */

    private String code;

    /**
     * 角色名称
     */

    private String name;

    /**
     * 角色描述
     */

    private String roleDesc;

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

    private String isDelete;

}
