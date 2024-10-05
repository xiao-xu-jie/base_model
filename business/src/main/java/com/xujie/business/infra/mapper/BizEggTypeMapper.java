package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizEggType;

public interface BizEggTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizEggType record);

    int insertSelective(BizEggType record);

    BizEggType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizEggType record);

    int updateByPrimaryKey(BizEggType record);
}