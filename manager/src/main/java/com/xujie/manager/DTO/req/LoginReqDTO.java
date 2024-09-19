package com.xujie.manager.DTO.req;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

/**
 * 登录请求DTO
 *
 * @author Xujie
 * @since 2024/9/17 21:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度为4-20")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为6-20")
    private String password;

}
