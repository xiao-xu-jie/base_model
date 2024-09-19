package com.xujie.manager.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xujie
 * @since 2024/9/19 10:40
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {
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
     * 密码
     */

    private String password;

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
