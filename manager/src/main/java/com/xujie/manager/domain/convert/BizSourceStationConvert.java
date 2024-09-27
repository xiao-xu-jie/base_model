package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizSourceStationAddReqDTO;
import com.xujie.manager.DTO.req.BizSourceStationQueryReqDTO;
import com.xujie.manager.DTO.res.BizSourceStationQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizSourceStationBO;
import com.xujie.manager.infra.DO.BizSourceStation;
import org.mapstruct.Mapper;

/**
 * (BizSourceStation)Convert 类
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@Mapper(componentModel = "spring")
public interface BizSourceStationConvert
    extends BaseConvert<
        BizSourceStationQueryResDTO,
        BizSourceStationAddReqDTO,
        BizSourceStationQueryReqDTO,
        BizSourceStationBO,
        BizSourceStation> {}
