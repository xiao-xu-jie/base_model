package com.xujie.manager;

import com.xujie.ManagerApplication;
import com.xujie.manager.infra.mapper.SysOperLogMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/9/25 21:46
 **/

@SpringBootTest(classes = ManagerApplication.class)
public class TestMy {

    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Test
    public void test(){
        System.out.println(sysOperLogMapper.selectById(1L));
    }
}
