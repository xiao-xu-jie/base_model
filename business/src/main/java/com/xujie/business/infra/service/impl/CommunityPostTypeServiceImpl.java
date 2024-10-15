package com.xujie.business.infra.service.impl;

import com.xujie.business.infra.DO.BizCommunityPostType;
import com.xujie.business.infra.mapper.BizCommunityPostTypeMapper;
import com.xujie.business.infra.service.CommunityPostTypeService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 社区帖子类型
 *
 * @author Xujie
 * @since 2024/10/14 12:58
 */
@Slf4j
@Service
public class CommunityPostTypeServiceImpl implements CommunityPostTypeService {
  @Resource private BizCommunityPostTypeMapper communityPostTypeMapper;

  @Override
  public List<BizCommunityPostType> selectList() {
    return communityPostTypeMapper.selectList(null);
  }
}
