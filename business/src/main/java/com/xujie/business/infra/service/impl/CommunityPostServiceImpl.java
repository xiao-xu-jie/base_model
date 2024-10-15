package com.xujie.business.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.common.enums.ShowStatusEnum;
import com.xujie.business.infra.DO.BizCommunityPost;
import com.xujie.business.infra.mapper.BizCommunityPostMapper;
import com.xujie.business.infra.service.CommunityPostService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 社区帖子服务实现
 *
 * @author Xujie
 * @since 2024/10/14 12:27
 */
@Slf4j
@Service
public class CommunityPostServiceImpl implements CommunityPostService {
  @Resource private BizCommunityPostMapper communityPostMapper;

  @Override
  public Page<BizCommunityPost> selectPage(
      BizCommunityPost bizCommunityPost, Integer pageNum, Integer pageSize) {
    Page<BizCommunityPost> page = new Page<>(pageNum, pageSize);
    LambdaQueryWrapper<BizCommunityPost> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper
        .eq(BizCommunityPost::getStatus, ShowStatusEnum.SHOW)
        .orderByDesc(BizCommunityPost::getCreateTime, BizCommunityPost::getUpdateTime)
        .eq(
            bizCommunityPost.getPostTypeId() != null,
            BizCommunityPost::getPostTypeId,
            bizCommunityPost.getPostTypeId())
        .like(
            StringUtils.isNotBlank(bizCommunityPost.getSearchText()),
            BizCommunityPost::getContent,
            bizCommunityPost.getSearchText())
        .or()
        .like(
            StringUtils.isNotBlank(bizCommunityPost.getSearchText()),
            BizCommunityPost::getTitle,
            bizCommunityPost.getSearchText())
        .or()
        .like(
            StringUtils.isNotBlank(bizCommunityPost.getSearchText()),
            BizCommunityPost::getPostTypeName,
            bizCommunityPost.getSearchText());
    return communityPostMapper.selectPage(page, lambdaQueryWrapper);
  }
}
