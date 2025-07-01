package com.xujie.business.mapper.user;

import com.mongoplus.conditions.query.QueryWrapper;
import com.xujie.business.entity.user.User;
import com.xujie.future.mongo.base.BaseMongoMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Xujie
 * @since 2025/6/30 13:20
 **/

@Repository
public class UserMapper extends BaseMongoMapper<User> {
    public boolean existsByUsername(String username) {
        QueryWrapper<User> eq = createMgoQuery()
                .eq(User::getUsername, username);
        return count(eq) > 0;
    }

    public boolean existsByPhone(String phoneNumber) {
        QueryWrapper<User> eq = createMgoQuery()
                .eq(User::getPhoneNumber, phoneNumber);
        return count(eq) > 0;

    }
}
