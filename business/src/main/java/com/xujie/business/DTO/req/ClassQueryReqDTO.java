package com.xujie.business.DTO.req;

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
    private Long good_id;
    /**
     * 手机号
     */
    private String user;
    /**
     * 密码
     */
    private String pass;
}
