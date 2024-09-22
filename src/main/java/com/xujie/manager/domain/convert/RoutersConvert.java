package com.xujie.manager.domain.convert;

/**
 * (SysRouters)Convert 类
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */

@Mapper(componentModel = "spring")
public interface RoutersConvert extends

        BaseConvert

                <RoutersQueryResDTO, RoutersAddReqDTO, RoutersQueryReqDTO, RoutersBO, SysRouters> {


}

