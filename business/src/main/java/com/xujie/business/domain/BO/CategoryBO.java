package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类BO
 * @author Xujie
 * @since 2024/9/27 08:52
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBO {
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

}
