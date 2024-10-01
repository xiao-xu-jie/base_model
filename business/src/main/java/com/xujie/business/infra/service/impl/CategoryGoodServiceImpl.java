package com.xujie.business.infra.service.impl;

import com.xujie.business.common.enums.GoodStatusEnum;
import com.xujie.business.infra.DO.BizCategory;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.mapper.BizCategoryMapper;
import com.xujie.business.infra.mapper.BizGoodMapper;
import com.xujie.business.infra.service.CategoryGoodService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Xujie
 * @since 2024/9/27 08:30
 */
@Slf4j
@Service
public class CategoryGoodServiceImpl implements CategoryGoodService {
  @Resource private BizCategoryMapper bizCategoryMapper;
  @Resource private BizGoodMapper bizGoodMapper;

  @Override
  public List<BizCategory> getCategoryListByEntity(BizCategory bizCategory) {
    bizCategory.setCategoryStatus(1);
    return bizCategoryMapper.getByAll(bizCategory);
  }

  @Override
  public List<BizGood> getGoodListByEntity(BizGood bizGood) {
    bizGood.setGoodStatus(GoodStatusEnum.UP);
    return bizGoodMapper.getByAll(bizGood);
  }
}
