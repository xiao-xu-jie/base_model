package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizCategoryAddReqDTO;
import com.xujie.manager.DTO.req.BizCategoryQueryReqDTO;
import com.xujie.manager.DTO.res.BizCategoryQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizCategoryBO;
import com.xujie.manager.infra.DO.BizCategory;
import org.mapstruct.Mapper;

/**
 * (BizCategory)Convert 类
 *
 * @author xujie
 * @since 2024-09-27 19:02:09
 */
@Mapper(componentModel = "spring")
public interface BizCategoryConvert
    extends BaseConvert<
        BizCategoryQueryResDTO,
        BizCategoryAddReqDTO,
        BizCategoryQueryReqDTO,
        BizCategoryBO,
        BizCategory> {}
