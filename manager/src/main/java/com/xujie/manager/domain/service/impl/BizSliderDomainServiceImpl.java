package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizSliderBO;
import com.xujie.manager.domain.convert.BizSliderConvert;
import com.xujie.manager.domain.service.BizSliderDomainService;
import com.xujie.manager.infra.DO.BizSlider;
import com.xujie.manager.infra.service.BizSliderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发布页面轮播(BizSlider)表服务实现类
 *
 * @author xujie
 * @since 2024-11-07 09:07:49
 */
@Slf4j
@Service
public class BizSliderDomainServiceImpl implements BizSliderDomainService {
  @Resource private BizSliderService bizSliderService;
  @Resource private BizSliderConvert bizSliderConvert;

  @Override
  public void add(BizSliderBO bizSliderBO) {
    BizSlider bizSlider = bizSliderConvert.convertBO2DO(bizSliderBO);
    bizSliderService.addOne(bizSlider);
  }

  @Override
  public Page<BizSliderBO> getPageList(BizSliderBO bizSliderBO, Integer pageNum, Integer pageSize) {
    BizSlider bizSlider = bizSliderConvert.convertBO2DO(bizSliderBO);
    Page<BizSlider> page = bizSliderService.getPageList(bizSlider, pageNum, pageSize);
    return bizSliderConvert.convertPageDO2BO(page);
  }

  @Override
  public void delete(Long[] ids) {
    bizSliderService.deleteBatch(ids);
  }

  @Override
  public void update(BizSliderBO bizSliderBO) {
    BizSlider bizSlider = bizSliderConvert.convertBO2DO(bizSliderBO);
    bizSliderService.updateOne(bizSlider.getId(), bizSlider);
  }
}
