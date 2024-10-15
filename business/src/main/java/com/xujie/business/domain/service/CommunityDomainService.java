package com.xujie.business.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.domain.BO.BizCommunityPostBO;
import com.xujie.business.domain.BO.BizCommunityPostTypeBO;
import java.util.List;

public interface CommunityDomainService {
  Page<BizCommunityPostBO> selectPage(
      BizCommunityPostBO bizCommunityPostBO, Integer pageNum, Integer pageSize);

  List<BizCommunityPostTypeBO> selectPostTypeList();
}
