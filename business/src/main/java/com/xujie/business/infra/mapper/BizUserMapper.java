package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizUser;

public interface BizUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizUser record);

    int insertSelective(BizUser record);

    BizUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizUser record);

    int updateByPrimaryKey(BizUser record);
}