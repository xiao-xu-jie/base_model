package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.OperLogAddReqDTO;
import com.xujie.manager.DTO.req.OperLogQueryReqDTO;
import com.xujie.manager.DTO.res.OperLogQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.OperLogBO;
import com.xujie.manager.infra.DO.SysOperLog;
import org.mapstruct.Mapper;

/**
 * (OperLog)Convert 类
 *
 * @author xujie
 * @since 2024-09-25 16:00:17
 */

@Mapper(componentModel = "spring")
public interface OperLogConvert extends BaseConvert<OperLogQueryResDTO, OperLogAddReqDTO, OperLogQueryReqDTO, OperLogBO, SysOperLog> {


}

