package com.xujie.business.DTO.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程查询DTO
 * @author Xujie
 * @since 2024/9/26 15:44
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQueryReqDTO {
    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Long good_id;
    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @NotBlank(message = "手机号不能为空")
    private String user;
    /**
     * 密码
     */
    @NotBlank
    private String pass;
}
