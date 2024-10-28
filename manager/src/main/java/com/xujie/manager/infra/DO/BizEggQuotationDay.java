package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_egg_quotation_day")
public class BizEggQuotationDay {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "type_id")
    private Long typeId;

    @TableField(value = "quotation_type")
    private Integer quotationType;

    @TableField(value = "avg_sale_price")
    private Double avgSalePrice;

    /**
     * 平均价
     */
    @TableField(value = "avg_num")
    private Double avgNum;

    /**
     * 最高价
     */
    @TableField(value = "max_num")
    private Double maxNum;

    /**
     * 最低价
     */
    @TableField(value = "min_num")
    private Double minNum;

    /**
     * 报价人数
     */
    @TableField(value = "people_number")
    private Integer peopleNumber;

    /**
     * 下降，持平，上升-1,0,1
     */
    @TableField(value = "`status`")
    private Integer status;

    @TableField(value = "data_time")
    private String dataTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_TYPE_ID = "type_id";

    public static final String COL_QUOTATION_TYPE = "quotation_type";

    public static final String COL_AVG_SALE_PRICE = "avg_sale_price";

    public static final String COL_AVG_NUM = "avg_num";

    public static final String COL_MAX_NUM = "max_num";

    public static final String COL_MIN_NUM = "min_num";

    public static final String COL_PEOPLE_NUMBER = "people_number";

    public static final String COL_STATUS = "status";

    public static final String COL_DATA_TIME = "data_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETE = "is_delete";
}