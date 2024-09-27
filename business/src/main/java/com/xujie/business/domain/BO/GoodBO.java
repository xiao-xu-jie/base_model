package com.xujie.business.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品BO
 * @author Xujie
 * @since 2024/9/27 08:52
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodBO {
    /**
     * 商品ID
     */

    private Long id;

    /**
     * 分类id
     */

    private Long categoryId;

    /**
     * 商品名称
     */

    private String goodName;

    /**
     * 商品描述
     */

    private String goodDesc;

    /**
     * 商品价格
     */

    private BigDecimal goodPrice;

    /**
     * 商品排序
     */

    private Integer goodRank;

    /**
     * 商品参数
     */

    private String goodParams;

    /**
     * 货源站ID
     */

    private Long stationId;

}
