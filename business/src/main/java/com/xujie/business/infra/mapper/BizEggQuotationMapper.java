package com.xujie.business.infra.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xujie.business.common.enums.ShowStatusEnum;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizEggQuotation;

public interface BizEggQuotationMapper extends BaseMapper<BizEggQuotation> {
	List<BizEggQuotation> getByAll(BizEggQuotation bizEggQuotation);


}