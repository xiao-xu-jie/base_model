package com.xujie.business.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BizOrderMapper extends BaseMapper<BizOrder> {
    List<BizOrder> findByAll(BizOrder bizOrder);


    int updateById(@Param("updated") BizOrder updated, @Param("id") Long id);


}