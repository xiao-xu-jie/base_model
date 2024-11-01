package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizEggQuotation;
import org.apache.ibatis.annotations.Param;

public interface BizEggQuotationMapper extends BaseMapper<BizEggQuotation> {
  Page<BizEggQuotation> getPageList(
      @Param(Constants.WRAPPER) QueryWrapper<BizEggQuotation> queryWrapper,
      Page<BizEggQuotation> page);
}
