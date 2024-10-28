package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizUserAddReqDTO;
import com.xujie.manager.DTO.req.BizUserQueryReqDTO;
import com.xujie.manager.DTO.res.BizUserQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizUserBO;
import com.xujie.manager.infra.DO.BizUser;
import org.mapstruct.Mapper;

/**
 * (BizUser)Convert 类
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@Mapper(componentModel = "spring")
public interface BizUserConvert
    extends BaseConvert<
        BizUserQueryResDTO, BizUserAddReqDTO, BizUserQueryReqDTO, BizUserBO, BizUser> {}
