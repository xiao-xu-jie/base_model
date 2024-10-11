package com.xujie.business.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizEggQuotationDay;
import java.util.List;

public interface BizEggQuotationDayMapper extends BaseMapper<BizEggQuotationDay> {
  int insertSelective(List<BizEggQuotationDay> bizEggQuotationDay);
}
