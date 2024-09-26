package com.xujie.business.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品查询响应DTO
 * @author Xujie
 * @since 2024/9/26 20:52
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodQueryResDTO {
    /**
     * 商品ID
     */

    private Long id;

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
     * 商品参数
     */

    private String goodParams;

}
