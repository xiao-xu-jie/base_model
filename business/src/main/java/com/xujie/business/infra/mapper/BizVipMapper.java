package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizVip;

public interface BizVipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizVip record);

    int insertSelective(BizVip record);

    BizVip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizVip record);

    int updateByPrimaryKey(BizVip record);
}