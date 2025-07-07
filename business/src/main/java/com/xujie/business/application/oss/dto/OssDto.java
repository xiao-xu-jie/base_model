package com.xujie.business.application.oss.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: AKang
 * @Description:
 * @CreateTime: 2025-05-10
 */

public class OssDto {

    /**
     * @description: Sts临时访问响应类
     * @author: AKang
     * @date: 2025/5/11 12:16
     **/
    @Data
    @ToString
    public static class StsResponse {
        /**
         * accessKeyId
         **/
        private String accessKeyId;
        /**
         * accessKeySecret
         **/
        private String accessKeySecret;
        /**
         * 临时凭证
         **/
        private String securityToken;
        /**
         * 桶名
         **/
        private String bucketName;
        /**
         * 地域名
         **/
        private String region;

        /**
         * 用户文件路径
         */
        private String filePath;
    }

}
