package com.xujie.business.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类商品查询响应DTO
 * @author Xujie
 * @since 2024/9/26 20:51
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGoodQueryResDTO {
    /**
     * 分类ID
     */

    private Long id;

    /**
     * 分类名称
     */

    private String categoryName;

    /**
     * 分类描述
     */

    private String categoryDesc;

    /**
     * 分类状态
     */

    private Integer categoryStatus;

    /**
     * 分类下的商品
     */
    private List<GoodQueryResDTO> goodList;
}
