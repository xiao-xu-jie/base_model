package com.xujie.business.entity.user;

/**
 * @author Xujie
 * @since 2025/6/30 13:10
 **/


import com.mongoplus.annotation.ID;
import com.mongoplus.annotation.collection.CollectionName;
import com.mongoplus.annotation.index.MongoIndex;
import com.mongoplus.annotation.index.MongoTextIndex;
import com.mongoplus.enums.IdTypeEnum;
import com.xujie.business.commom.enums.user.UserAccountStatus;
import com.xujie.business.commom.enums.user.UserGender;
import com.xujie.business.commom.utils.timer.Timer;
import com.xujie.future.contract.enums.DelEnum;
import com.xujie.future.mongo.base.DbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import static com.xujie.business.commom.constants.user.UserConstant.USER_DEFAULT_AVATAR;


/**
 * 用户实体
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@CollectionName("user")
@MongoTextIndex(fields = {"userName"})
@FieldNameConstants
public class User implements DbEntity {
    /**
     * 用户Id
     */
    @ID(type = IdTypeEnum.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @MongoIndex
    private String username;

    /**
     * 昵称
     */
    @MongoIndex
    private String nickname = createRandomNickName();

    /**
     * 用户简介
     */
    @MongoIndex
    private String userInstruction = "无";

    /**
     * 用户地区
     */
    @MongoIndex
    private String userArea = "";

    /**
     * 密码
     */
    @MongoIndex
    private String password;

    /**
     * 头像地址
     */
    private String avatar = USER_DEFAULT_AVATAR;

    /**
     * 电话号码
     */
    @MongoIndex(unique = true)
    private String phoneNumber;

    /**
     * 邮箱
     */
    @MongoIndex(unique = true)
    private String email = "";

    /**
     * 真实姓名
     */
    private String realName = "";

    /**
     * 性别 0-男 1-女
     */
    private Integer sex = UserGender.MAN.getCode();

    /**
     * 用户账号状态 0-正常 1-禁用
     */
    private Integer status = UserAccountStatus.NORMAL.getCode();

    /**
     * 删除状态
     */
    @MongoIndex
    private Integer del = DelEnum.UN_DEL.getCode();

    /**
     * 创建时间
     */
    private Long createTime = Timer.currentTimestamp();

    /**
     * 更新时间
     */
    private Long updateTime = Timer.currentTimestamp();

    public static String createRandomNickName() {
        // 生成随机昵称的逻辑
        return "User" + (int) (Math.random() * 1000);
    }
}
