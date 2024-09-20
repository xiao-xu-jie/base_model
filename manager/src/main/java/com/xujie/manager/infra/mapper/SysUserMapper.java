package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.manager.infra.DO.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {
    int updateById(@Param("updated") SysUser updated, @Param("id") Long id);


}