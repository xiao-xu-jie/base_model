package com.xujie.business.user;

import com.xujie.business.BusinessApplication;
import com.xujie.business.entity.user.User;
import com.xujie.business.mapper.user.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2025/6/30 13:21
 **/

@SpringBootTest(classes = BusinessApplication.class)
public class TestUser {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setNickname("Test User");
        user.setEmail("testEmail");
        user.setAvatar("testAvatar.png");
        user.setPhoneNumber("1234567890");
        userMapper.save(user);
    }
}
