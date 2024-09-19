package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.manager.infra.DO.SysRole;
import com.xujie.manager.infra.DO.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<SysRole> selectRoleByUserId(Long loginId);
}