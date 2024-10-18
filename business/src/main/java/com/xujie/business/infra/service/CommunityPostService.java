package com.xujie.business.infra.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.infra.DO.BizCommunityPost;
import java.util.List;

public interface CommunityPostService {
  Page<BizCommunityPost> selectPage(
      BizCommunityPost bizCommunityPost, Integer pageNum, Integer pageSize);

  BizCommunityPost getByEntity(BizCommunityPost bizCommunityPost);

  void saveCommunityPost(BizCommunityPost bizCommunityPost);

  List<BizCommunityPost> getListByEntity(BizCommunityPost build);

  void updateCommunityPost(BizCommunityPost bizCommunityPost);
}
