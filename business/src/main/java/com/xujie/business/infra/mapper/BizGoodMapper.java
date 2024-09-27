package com.xujie.business.infra.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizGood;

public interface BizGoodMapper extends BaseMapper<BizGood> {
    List<BizGood> getByAll(BizGood bizGood);
}