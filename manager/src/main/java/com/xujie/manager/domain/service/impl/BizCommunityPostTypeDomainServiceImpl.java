package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizCommunityPostTypeBO;
import com.xujie.manager.domain.convert.BizCommunityPostTypeConvert;
import com.xujie.manager.domain.service.BizCommunityPostTypeDomainService;
import com.xujie.manager.infra.DO.BizCommunityPostType;
import com.xujie.manager.infra.service.BizCommunityTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizCommunityPostType)表服务实现类
 *
 * @author xujie
 * @since 2024-10-31 23:02:23
 */
@Slf4j
@Service
public class BizCommunityPostTypeDomainServiceImpl implements BizCommunityPostTypeDomainService {
  @Resource private BizCommunityTypeService bizCommunityTypeService;
  @Resource private BizCommunityPostTypeConvert bizCommunityPostTypeConvert;

  @Override
  public void add(BizCommunityPostTypeBO bizCommunityPostTypeBO) {
    bizCommunityTypeService.addOne(
        bizCommunityPostTypeConvert.convertBO2DO(bizCommunityPostTypeBO));
  }

  @Override
  public Page<BizCommunityPostTypeBO> getPageList(
      BizCommunityPostTypeBO bizCommunityPostTypeBO, Integer pageNum, Integer pageSize) {
    Page<BizCommunityPostType> pageList =
        bizCommunityTypeService.getPageList(
            bizCommunityPostTypeConvert.convertBO2DO(bizCommunityPostTypeBO), pageNum, pageSize);
    return bizCommunityPostTypeConvert.convertPageDO2BO(pageList);
  }

  @Override
  public void delete(Long[] ids) {
    bizCommunityTypeService.deleteBatch(ids);
  }

  @Override
  public void update(BizCommunityPostTypeBO bizCommunityPostTypeBO) {
    bizCommunityTypeService.updateOne(
        bizCommunityPostTypeBO.getId(),
        bizCommunityPostTypeConvert.convertBO2DO(bizCommunityPostTypeBO));
  }
}
