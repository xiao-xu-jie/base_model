package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizEggQuotationDay;

public interface BizEggQuotationDayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizEggQuotationDay record);

    int insertSelective(BizEggQuotationDay record);

    BizEggQuotationDay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizEggQuotationDay record);

    int updateByPrimaryKey(BizEggQuotationDay record);
}