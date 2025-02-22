package com.xujie.business.infra.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.xujie.business.common.enums.OrderStatusEnum;
import com.xujie.business.common.enums.SubmitStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_order")
public class BizOrder {
    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private Long orderNo;

    /**
     * 商品ID
     */
    @TableField(value = "good_id")
    private Long goodId;

    /**
     * 商品名称
     */
    @TableField(value = "good_name")
    private String goodName;

    /**
     * 商品描述
     */
    @TableField(value = "good_desc")
    private String goodDesc;

    /**
     * 商品价格
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    /**
     * 学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 课程ID 数组
     */
    @TableField(value = "class_info")
    private String classInfo;
    /**
     * 平台ID
     */
    @TableField(value = "platform_id")
    private Integer platformId;
    /**
     * 站点URL
     */
    @TableField(value = "station_url")
    private String stationUrl;
    /**
     * 平台uid
     */
    @TableField(value = "platform_uid")
    private String platformUid;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private OrderStatusEnum orderStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 提交状态
     */
    @TableField(value = "submit_status")
    private SubmitStatusEnum submitStatus;

    /**
     * 提交错误信息
     */
    @TableField(value = "error_data")
    private String errorData;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 版本号
     */
    @TableField(value = "version")
    @Version
    private Long version;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_GOOD_ID = "good_id";

    public static final String COL_GOOD_NAME = "good_name";

    public static final String COL_GOOD_DESC = "good_desc";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_SCHOOL = "school";

    public static final String COL_PHONE = "phone";

    public static final String COL_PASSWORD = "password";

    public static final String COL_CLASS_ID = "class_id";

    public static final String COL_ORDER_STATUS = "order_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_PAY_TIME = "pay_time";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_VERSION = "version";
}
