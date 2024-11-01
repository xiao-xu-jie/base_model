package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizEggQuotationAddReqDTO;
import com.xujie.manager.DTO.req.BizEggQuotationQueryReqDTO;
import com.xujie.manager.DTO.res.BizEggQuotationQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizEggQuotationBO;
import com.xujie.manager.infra.DO.BizEggQuotation;
import org.mapstruct.Mapper;

/**
 * (BizEggQuotation)Convert 类
 *
 * @author xujie
 * @since 2024-11-01 23:29:20
 */
@Mapper(componentModel = "spring")
public interface BizEggQuotationConvert
    extends BaseConvert<
        BizEggQuotationQueryResDTO,
        BizEggQuotationAddReqDTO,
        BizEggQuotationQueryReqDTO,
        BizEggQuotationBO,
        BizEggQuotation> {}
