package com.xujie.manager.domain.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.DTO.req.UserAddReqDTO;
import com.xujie.manager.DTO.req.UserQueryReqDTO;
import com.xujie.manager.DTO.res.UserQueryResDTO;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.infra.DO.SysUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {


    SysUser convertBO2DO(UserBO userBO);

    Page<UserBO> convertPageDO2BO(Page<SysUser> page);

    UserBO convertQueryReqDTO2BO(UserQueryReqDTO userQueryReqDTO);

    Page<UserQueryResDTO> convertPageBO2DTO(Page<UserBO> userPageList);

    UserBO convertAddReqDTO2BO(UserAddReqDTO userAddReqDTO);

    UserBO convertDO2BO(SysUser user);
}
