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
@TableName(value = "biz_egg_quotation")
public class BizEggQuotation {
    /**
     * 报价ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 蛋类型ID
     */
    @TableField(value = "egg_type_id")
    private Long eggTypeId;

    /**
     * 蛋类型名称
     */
    @TableField(value = "egg_type_name")
    private String eggTypeName;

    /**
     * 报价地址
     */
    @TableField(value = "quotation_location")
    private String quotationLocation;

    /**
     * 最高价
     */
    @TableField(value = "quotation_max_price")
    private Double quotationMaxPrice;

    /**
     * 参考价
     */
    @TableField(value = "quotation_avg_price")
    private Double quotationAvgPrice;

    /**
     * 最低价
     */
    @TableField(value = "quotation_min_price")
    private Double quotationMinPrice;

    /**
     * 是否显示
     */
    @TableField(value = "quotation_status")
    private Integer quotationStatus;

    /**
     * 波动
     */
    @TableField(value = "quotation_float_status")
    private Integer quotationFloatStatus;

    /**
     * 0-收购价，1-出售价
     */
    @TableField(value = "quotation_type")
    private Integer quotationType;

    /**
     * 发布日期（yyyy_mm_dd)
     */
    @TableField(value = "data_date")
    private String dataDate;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_EGG_TYPE_ID = "egg_type_id";

    public static final String COL_EGG_TYPE_NAME = "egg_type_name";

    public static final String COL_QUOTATION_LOCATION = "quotation_location";

    public static final String COL_QUOTATION_MAX_PRICE = "quotation_max_price";

    public static final String COL_QUOTATION_AVG_PRICE = "quotation_avg_price";

    public static final String COL_QUOTATION_MIN_PRICE = "quotation_min_price";

    public static final String COL_QUOTATION_STATUS = "quotation_status";

    public static final String COL_QUOTATION_FLOAT_STATUS = "quotation_float_status";

    public static final String COL_QUOTATION_TYPE = "quotation_type";

    public static final String COL_DATA_DATE = "data_date";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETE = "is_delete";
}