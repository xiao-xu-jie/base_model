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
public class BizEggType {
    /**
    * 类型ID
    */
    private Long id;

    /**
    * 类型名称
    */
    private String eggTypeName;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}