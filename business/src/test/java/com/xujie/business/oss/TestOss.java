package com.xujie.business.oss;

import com.xujie.business.BusinessApplication;
import com.xujie.business.application.oss.OssUtil;
import com.xujie.business.commom.enums.oss.OssUploadEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Xujie
 * @since 2025/7/7 23:10
 **/

@Slf4j
@SpringBootTest(classes = BusinessApplication.class)
public class TestOss {
    @Resource
    private OssUtil ossUtil;

    @Test
    public void testUserUpload() throws FileNotFoundException {
        // 测试上传文件到OSS
        File file = new File("C:\\Users\\Administrator\\Desktop\\新建文本文档 (3).txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            // 打印内容
            ossUtil.uploadFile(OssUploadEnum.DJ_USER_FILE, "test.txt", fileInputStream);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
