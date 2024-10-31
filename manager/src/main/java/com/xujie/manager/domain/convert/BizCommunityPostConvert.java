package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizCommunityPostAddReqDTO;
import com.xujie.manager.DTO.req.BizCommunityPostQueryReqDTO;
import com.xujie.manager.DTO.res.BizCommunityPostQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizCommunityPostBO;
import com.xujie.manager.infra.DO.BizCommunityPost;
import org.mapstruct.Mapper;

/**
 * (BizCommunityPost)Convert 类
 *
 * @author xujie
 * @since 2024-10-31 23:48:36
 */
@Mapper(componentModel = "spring")
public interface BizCommunityPostConvert
    extends BaseConvert<
        BizCommunityPostQueryResDTO,
        BizCommunityPostAddReqDTO,
        BizCommunityPostQueryReqDTO,
        BizCommunityPostBO,
        BizCommunityPost> {}
