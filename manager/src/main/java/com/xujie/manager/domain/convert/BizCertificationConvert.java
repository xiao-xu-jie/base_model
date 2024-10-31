package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizCertificationAddReqDTO;
import com.xujie.manager.DTO.req.BizCertificationQueryReqDTO;
import com.xujie.manager.DTO.res.BizCertificationQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizCertificationBO;
import com.xujie.manager.infra.DO.BizCertification;
import org.mapstruct.Mapper;

/**
 * (BizCertification)Convert 类
 *
 * @author xujie
 * @since 2024-10-31 11:46:35
 */
@Mapper(componentModel = "spring")
public interface BizCertificationConvert
    extends BaseConvert<
        BizCertificationQueryResDTO,
        BizCertificationAddReqDTO,
        BizCertificationQueryReqDTO,
        BizCertificationBO,
        BizCertification> {}
