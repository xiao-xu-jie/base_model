package com.xujie.manager.domain.convert;

/**
 * (SysSystemLog)Convert 类
 *
 * @author xujie
 * @since 2024-09-22 13:59:55
 */

@Mapper(componentModel = "spring")
public interface SystemLogConvert extends

        BaseConvert

                <SystemLogQueryResDTO, SystemLogAddReqDTO, SystemLogQueryReqDTO, SystemLogBO, SysSystemLog> {


}

