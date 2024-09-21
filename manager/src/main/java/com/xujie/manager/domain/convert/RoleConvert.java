package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.RoleAddReqDTO;
import com.xujie.manager.DTO.req.RoleQueryReqDTO;
import com.xujie.manager.DTO.res.RoleQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.infra.DO.SysRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleConvert extends BaseConvert<RoleQueryResDTO, RoleAddReqDTO, RoleQueryReqDTO, RoleBO, SysRole> {

}
