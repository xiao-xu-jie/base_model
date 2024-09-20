package com.xujie.manager.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户查询返回DTO
 *
 * @author Xujie
 * @since 2024/9/19 11:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryResDTO {
    /**
     * 用户头像
     */

    private String avatar;

    /**
     * 用户ID
     */

    private Long id;


    /**
     * 用户名
     */

    private String username;


    /**
     * 名称
     */

    private String name;
    /**
     * 性别
     */

    private Integer sex;

    /**
     * 邮箱
     */

    private String email;

    /**
     * openId
     */

    private Integer openId;

    /**
     * 手机号
     */

    private String phone;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 创建时间
     */

    private Date createTime;

}
