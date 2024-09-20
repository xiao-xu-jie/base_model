package com.xujie.manager.domain.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.DTO.res.RoleQueryDTO;
import com.xujie.manager.domain.BO.RoleBO;
import com.xujie.manager.infra.DO.SysRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleConvert {
    List<RoleBO> convertDO2BO(List<SysRole> allRoleByEntity);

    SysRole convertBO2DO(RoleBO roleBO);

    Page<RoleBO> convertPageDO2BO(Page<SysRole> rolePageList);

    List<RoleQueryDTO> convertListBO2DTO(List<RoleBO> allRoleList);
}
