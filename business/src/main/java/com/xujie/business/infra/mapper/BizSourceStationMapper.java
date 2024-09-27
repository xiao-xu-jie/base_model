package com.xujie.business.infra.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizSourceStation;

public interface BizSourceStationMapper extends BaseMapper<BizSourceStation> {
    List<BizSourceStation> getByAll(BizSourceStation bizSourceStation);


}