package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分类商品
 * @author Xujie
 * @since 2024/9/27 08:27
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGoodBO {
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
     * 商品列表
     */
    private List<GoodBO> goodList;
}
