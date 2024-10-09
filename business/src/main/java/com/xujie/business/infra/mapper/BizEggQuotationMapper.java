package com.xujie.business.infra.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.infra.DO.BizEggQuotation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizEggQuotationMapper extends BaseMapper<BizEggQuotation> {
  List<BizEggQuotation> getByAll(BizEggQuotation bizEggQuotation);

  Page<BizEggQuotation> getByPage(
      @Param(Constants.WRAPPER) QueryWrapper<BizEggQuotation> wrapper, Page<BizEggQuotation> page);
}
