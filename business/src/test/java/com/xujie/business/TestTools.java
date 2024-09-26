package com.xujie.business;

import com.xujie.business.DTO.req.ClassQueryReqDTO;
import com.xujie.business.DTO.res.ClassQueryResDTO;
import com.xujie.business.common.adapters.impl.PlatForm29AdapterImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/9/16 11:23
 **/

@SpringBootTest(classes = businessApplication.class)
public class TestTools {
    @Resource
    private PlatForm29AdapterImpl platForm29Adapter;

    @Test
    public void test() {
    }
}
