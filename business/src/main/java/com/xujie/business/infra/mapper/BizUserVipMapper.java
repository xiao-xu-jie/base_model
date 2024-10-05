package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizUserVip;

public interface BizUserVipMapper {
    int insert(BizUserVip record);

    int insertSelective(BizUserVip record);
}