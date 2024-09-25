package com.xujie.manager.infra.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.manager.infra.DO.SysOperLog;

public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    int updateById(@Param("updated")SysOperLog updated,@Param("id")Long id);


}
