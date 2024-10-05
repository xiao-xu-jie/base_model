package com.xujie.business.domain.BO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizEggQuotation {
    /**
    * 报价ID
    */
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 蛋类型ID
    */
    private Long eggTypeId;

    /**
    * 蛋类型名称
    */
    private String eggTypeName;

    /**
    * 报价地址
    */
    private String quotationLocation;

    /**
    * 是否显示
    */
    private Integer quotationStatus;

    /**
    * 0-收购价，1-出售价
    */
    private Integer quotationType;

    /**
    * 发布日期（yyyy_mm_dd)
    */
    private Date dataDate;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}