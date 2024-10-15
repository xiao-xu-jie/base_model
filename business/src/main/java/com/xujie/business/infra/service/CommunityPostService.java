package com.xujie.business.infra.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.infra.DO.BizCommunityPost;

public interface CommunityPostService {
  Page<BizCommunityPost> selectPage(
      BizCommunityPost bizCommunityPost, Integer pageNum, Integer pageSize);
}
