package com.xujie.business.entity.user;

/**
 * @author Xujie
 * @since 2025/6/30 13:32
 **/

import com.mongoplus.annotation.ID;
import com.mongoplus.annotation.collection.CollectionName;
import com.mongoplus.annotation.index.MongoIndex;
import com.mongoplus.enums.IdTypeEnum;
import com.xujie.business.commom.utils.timer.Timer;
import com.xujie.future.contract.enums.DelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * 用户实体
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@CollectionName("userWxAccountBind")
@FieldNameConstants
public class UserWxAccountBind {

    @ID(type = IdTypeEnum.AUTO)
    private Long id;

    @MongoIndex(unique = true)
    private Long userId;

    @MongoIndex(unique = true)
    private Long wxOpenId;

    @MongoIndex(unique = true)
    private String wxUnionId;

    @MongoIndex
    private String nickName;

    @MongoIndex
    private String avatarUrl;

    /**
     * 删除状态
     */
    @MongoIndex
    private Integer del = DelEnum.UN_DEL.getCode();

    /**
     * 创建时间
     */
    @MongoIndex
    private Long createTime = Timer.currentTimestamp();

    /**
     * 更新时间
     */
    @MongoIndex
    private Long updateTime = Timer.currentTimestamp();
}
