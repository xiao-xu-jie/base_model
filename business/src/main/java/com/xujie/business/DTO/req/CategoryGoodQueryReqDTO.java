package com.xujie.business.DTO.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品查询请求DTO
 * @author Xujie
 * @since 2024/9/26 23:29
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGoodQueryReqDTO {
    /**
     * 查询名称
     */

    private String searchName;
}
