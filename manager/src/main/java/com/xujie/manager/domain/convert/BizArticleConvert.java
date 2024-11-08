package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizArticleAddReqDTO;
import com.xujie.manager.DTO.req.BizArticleQueryReqDTO;
import com.xujie.manager.DTO.res.BizArticleQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizArticleBO;
import com.xujie.manager.infra.DO.BizArticle;
import org.mapstruct.Mapper;

/**
 * (BizArticle)Convert 类
 *
 * @author xujie
 * @since 2024-11-08 08:40:33
 */
@Mapper(componentModel = "spring")
public interface BizArticleConvert
    extends BaseConvert<
        BizArticleQueryResDTO,
        BizArticleAddReqDTO,
        BizArticleQueryReqDTO,
        BizArticleBO,
        BizArticle> {}
