package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.UserAddReqDTO;
import com.xujie.manager.DTO.req.UserQueryReqDTO;
import com.xujie.manager.DTO.res.UserQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.infra.DO.SysUser;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserConvert extends BaseConvert<UserQueryResDTO, UserAddReqDTO, UserQueryReqDTO, UserBO, SysUser> {


}
