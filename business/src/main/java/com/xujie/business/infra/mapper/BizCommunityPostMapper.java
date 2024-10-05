package com.xujie.business.infra.mapper;

import com.xujie.business.domain.BO.BizCommunityPost;

public interface BizCommunityPostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizCommunityPost record);

    int insertSelective(BizCommunityPost record);

    BizCommunityPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizCommunityPost record);

    int updateByPrimaryKey(BizCommunityPost record);
}