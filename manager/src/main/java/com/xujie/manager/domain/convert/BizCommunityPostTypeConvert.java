package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizCommunityPostTypeAddReqDTO;
import com.xujie.manager.DTO.req.BizCommunityPostTypeQueryReqDTO;
import com.xujie.manager.DTO.res.BizCommunityPostTypeQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizCommunityPostTypeBO;
import com.xujie.manager.infra.DO.BizCommunityPostType;
import org.mapstruct.Mapper;

/**
 * (BizCommunityPostType)Convert 类
 *
 * @author xujie
 * @since 2024-10-31 23:02:23
 */
@Mapper(componentModel = "spring")
public interface BizCommunityPostTypeConvert
    extends BaseConvert<
        BizCommunityPostTypeQueryResDTO,
        BizCommunityPostTypeAddReqDTO,
        BizCommunityPostTypeQueryReqDTO,
        BizCommunityPostTypeBO,
        BizCommunityPostType> {}
