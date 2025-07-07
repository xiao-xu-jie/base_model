package com.xujie.business.commom.enums.oss;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OssUploadEnum {
    /**
     * 用户文件保存
     */
    DJ_USER_FILE(OssBucketEnum.KNOWLEDGE_BUCKET, "dj830/user/");


    private final OssBucketEnum bucket;
    private final String path;
}
