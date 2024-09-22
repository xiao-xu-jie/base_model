package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.SystemLogAddReqDTO;
import com.xujie.manager.DTO.req.SystemLogQueryReqDTO;
import com.xujie.manager.DTO.res.SystemLogQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.SystemLogBO;
import com.xujie.manager.infra.DO.SysSystemLog;
import org.mapstruct.Mapper;

/**
 * (SysSystemLog)Convert 类
 *
 * @author xujie
 * @since 2024-09-22 10:18:06
 */

@Mapper(componentModel = "spring")
public interface SystemLogConvert extends

        BaseConvert

                <SystemLogQueryResDTO, SystemLogAddReqDTO, SystemLogQueryReqDTO, SystemLogBO, SysSystemLog> {


}

