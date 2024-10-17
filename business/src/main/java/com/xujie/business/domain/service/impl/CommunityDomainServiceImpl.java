package com.xujie.business.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.convert.CommunityPostConvert;
import com.xujie.business.domain.BO.BizCommunityPostBO;
import com.xujie.business.domain.BO.BizCommunityPostTypeBO;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.domain.service.CommunityDomainService;
import com.xujie.business.domain.service.UserDomainService;
import com.xujie.business.infra.DO.BizCommunityPost;
import com.xujie.business.infra.DO.BizCommunityPostType;
import com.xujie.business.infra.service.CommunityPostService;
import com.xujie.business.infra.service.CommunityPostTypeService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 社区领域服务
 *
 * @author Xujie
 * @since 2024/10/14 12:47
 */
@Slf4j
@Service
public class CommunityDomainServiceImpl implements CommunityDomainService {
  @Resource private CommunityPostService communityPostService;
  @Resource private CommunityPostTypeService communityPostTypeService;
  @Resource private UserDomainService userDomainService;
  @Resource private CommunityPostConvert communityPostConvert;

  @Override
  public Page<BizCommunityPostBO> selectPage(
      BizCommunityPostBO bizCommunityPostBO, Integer pageNum, Integer pageSize) {
    Page<BizCommunityPost> bizCommunityPostPage =
        communityPostService.selectPage(
            communityPostConvert.convertBO2DO(bizCommunityPostBO), pageNum, pageSize);

    return communityPostConvert.convertDOPage2BOPage(bizCommunityPostPage);
  }

  @Override
  public List<BizCommunityPostTypeBO> selectPostTypeList() {
    List<BizCommunityPostType> bizCommunityPostTypes = communityPostTypeService.selectList();
    return communityPostConvert.convertPostTypeDOList2BOList(bizCommunityPostTypes);
  }

  @Override
  public BizCommunityPostBO getById(Long id) {
    BizCommunityPost byEntity =
        communityPostService.getByEntity(BizCommunityPost.builder().id(id).build());
    ConditionCheck.nullAndThrow(byEntity, new CustomException("帖子不存在"));
    BizUserBO userProfile = userDomainService.getUserProfile(byEntity.getUserId());
    BizCommunityPostBO bizCommunityPostBO = communityPostConvert.convertDO2BO(byEntity);
    bizCommunityPostBO.setUserInfo(userProfile);
    return bizCommunityPostBO;
  }

  @Override
  public void save(BizCommunityPostBO bizCommunityPostBO) {
    bizCommunityPostBO.setUserId(StpUtil.getLoginIdAsLong());
    communityPostService.saveCommunityPost(communityPostConvert.convertBO2DO(bizCommunityPostBO));
  }
}
