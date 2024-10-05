package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizCommunityPostType;

public interface BizCommunityPostTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizCommunityPostType record);

    int insertSelective(BizCommunityPostType record);

    BizCommunityPostType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizCommunityPostType record);

    int updateByPrimaryKey(BizCommunityPostType record);
}