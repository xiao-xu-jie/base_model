package com.xujie.business.DTO.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信订单创建请求DTO
 * @author Xujie
 * @since 2024/9/27 16:16
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxOrderCreateReqDTO {
    /**
     * 商品名称
     */

    private String goodName;

    /**
     * 商品说明
     */

    private String orderDesc;

    /**
     * 订单价格（分）
     */

    private Integer price;

    /**
     * 站点ID
     */

    private Long siteId;

    /**
     * 站点Key
     */

    private String siteKey;

}
