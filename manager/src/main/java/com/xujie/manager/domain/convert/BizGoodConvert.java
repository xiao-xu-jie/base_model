package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizGoodAddReqDTO;
import com.xujie.manager.DTO.req.BizGoodQueryReqDTO;
import com.xujie.manager.DTO.res.BizGoodQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizGoodBO;
import com.xujie.manager.infra.DO.BizGood;
import org.mapstruct.Mapper;

/**
 * (BizGood)Convert 类
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@Mapper(componentModel = "spring")
public interface BizGoodConvert
    extends BaseConvert<
        BizGoodQueryResDTO, BizGoodAddReqDTO, BizGoodQueryReqDTO, BizGoodBO, BizGood> {}
