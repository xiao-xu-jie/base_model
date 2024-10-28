package com.xujie.manager.infra.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "biz_article")
public class BizArticle {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 略缩图
     */
    @TableField(value = "article_show_img")
    private String articleShowImg;

    /**
     * 文章标题
     */
    @TableField(value = "article_title")
    private String articleTitle;

    /**
     * 文章描述
     */
    @TableField(value = "article_desc")
    private String articleDesc;

    /**
     * 文章内容
     */
    @TableField(value = "article_content")
    private String articleContent;

    /**
     * 文章来源
     */
    @TableField(value = "article_source")
    private String articleSource;

    /**
     * 文章额外信息
     */
    @TableField(value = "article_extra")
    private String articleExtra;

    /**
     * 是否显示
     */
    @TableField(value = "is_show")
    private Integer isShow;

    /**
     * 文章排序
     */
    @TableField(value = "article_rank")
    private Integer articleRank;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "is_delete")
    private Integer isDelete;

    public static final String COL_ID = "id";

    public static final String COL_ARTICLE_SHOW_IMG = "article_show_img";

    public static final String COL_ARTICLE_TITLE = "article_title";

    public static final String COL_ARTICLE_DESC = "article_desc";

    public static final String COL_ARTICLE_CONTENT = "article_content";

    public static final String COL_ARTICLE_SOURCE = "article_source";

    public static final String COL_ARTICLE_EXTRA = "article_extra";

    public static final String COL_IS_SHOW = "is_show";

    public static final String COL_ARTICLE_RANK = "article_rank";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETE = "is_delete";
}