package com.xujie.business.infra.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xujie.business.common.enums.OrderStatusEnum;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizOrder;

public interface BizOrderMapper extends BaseMapper<BizOrder> {
    List<BizOrder> findByAll(BizOrder bizOrder);


    int updateById(@Param("updated")BizOrder updated,@Param("id")Long id);


}