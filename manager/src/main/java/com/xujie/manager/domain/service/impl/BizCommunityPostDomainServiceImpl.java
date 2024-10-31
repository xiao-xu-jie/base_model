package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizCommunityPostBO;
import com.xujie.manager.domain.convert.BizCommunityPostConvert;
import com.xujie.manager.domain.service.BizCommunityPostDomainService;
import com.xujie.manager.infra.DO.BizCommunityPost;
import com.xujie.manager.infra.service.BizCommunityService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizCommunityPost)表服务实现类
 *
 * @author xujie
 * @since 2024-10-31 23:48:36
 */
@Slf4j
@Service
public class BizCommunityPostDomainServiceImpl implements BizCommunityPostDomainService {
  @Resource private BizCommunityService bizCommunityService;
  @Resource private BizCommunityPostConvert bizCommunityPostConvert;

  @Override
  public void add(BizCommunityPostBO bizCommunityPostBO) {
    bizCommunityService.addOne(bizCommunityPostConvert.convertBO2DO(bizCommunityPostBO));
  }

  @Override
  public Page<BizCommunityPostBO> getPageList(
      BizCommunityPostBO bizCommunityPostBO, Integer pageNum, Integer pageSize) {
    Page<BizCommunityPost> pageList =
        bizCommunityService.getPageList(
            bizCommunityPostConvert.convertBO2DO(bizCommunityPostBO), pageNum, pageSize);
    return bizCommunityPostConvert.convertPageDO2BO(pageList);
  }

  @Override
  public void delete(Long[] ids) {
    bizCommunityService.deleteBatch(ids);
  }

  @Override
  public void update(BizCommunityPostBO bizCommunityPostBO) {
    bizCommunityService.updateOne(
        bizCommunityPostBO.getId(), bizCommunityPostConvert.convertBO2DO(bizCommunityPostBO));
  }
}
