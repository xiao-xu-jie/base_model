package com.xujie.business.commom.enums.oss;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 维护桶的枚举
 */
@Getter
@AllArgsConstructor
public enum OssBucketEnum {
    KNOWLEDGE_BUCKET("test-3285503539-oss", "oss-cn-hangzhou", "https://oss-cn-hangzhou.aliyuncs.com");
    private final String bucketName;
    private final String region;
    private final String endpoint;
}
