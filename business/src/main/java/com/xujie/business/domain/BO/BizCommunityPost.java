package com.xujie.business.domain.BO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizCommunityPost {
    /**
    * 发布ID
    */
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 发布类型ID
    */
    private Long postTypeId;

    /**
    * 发布类型名称
    */
    private String postTypeName;

    /**
    * 发布标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 图片数组
    */
    private String imgArray;

    /**
    * 状态 0,1
    */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}