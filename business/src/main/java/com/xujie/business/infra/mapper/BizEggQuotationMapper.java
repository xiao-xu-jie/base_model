package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizEggQuotation;

public interface BizEggQuotationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizEggQuotation record);

    int insertSelective(BizEggQuotation record);

    BizEggQuotation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizEggQuotation record);

    int updateByPrimaryKey(BizEggQuotation record);
}