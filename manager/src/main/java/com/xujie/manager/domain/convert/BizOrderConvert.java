package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizOrderAddReqDTO;
import com.xujie.manager.DTO.req.BizOrderQueryReqDTO;
import com.xujie.manager.DTO.res.BizOrderQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizOrderBO;
import com.xujie.manager.infra.DO.BizOrder;
import org.mapstruct.Mapper;

/**
 * (BizOrder)Convert 类
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@Mapper(componentModel = "spring")
public interface BizOrderConvert
    extends BaseConvert<
        BizOrderQueryResDTO, BizOrderAddReqDTO, BizOrderQueryReqDTO, BizOrderBO, BizOrder> {}
