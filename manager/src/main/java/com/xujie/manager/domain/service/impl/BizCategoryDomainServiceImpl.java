package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.domain.BO.BizCategoryBO;
import com.xujie.manager.domain.convert.BizCategoryConvert;
import com.xujie.manager.domain.service.BizCategoryDomainService;
import com.xujie.manager.infra.DO.BizCategory;
import com.xujie.manager.infra.service.BizCategoryService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizCategory)表服务实现类
 *
 * @author xujie
 * @since 2024-09-27 19:02:13
 */
@Slf4j
@Service
public class BizCategoryDomainServiceImpl implements BizCategoryDomainService {
  @Resource private BizCategoryService baseService;
  @Resource private BizCategoryConvert baseConvert;

  @Override
  public void add(BizCategoryBO bizCategoryBO) {
    BizCategory bizCategory = baseConvert.convertBO2DO(bizCategoryBO);
    Long l = baseService.addOne(bizCategory);
    log.info("add BizCategory success, id: {}", l);
    ConditionCheck.nullAndThrow(l, new CustomException("分类添加失败"));
  }

  @Override
  public Page<BizCategoryBO> getPageList(
      BizCategoryBO bizCategoryBO, Integer pageNum, Integer pageSize) {
    Page<BizCategory> pageList =
        baseService.getPageList(baseConvert.convertBO2DO(bizCategoryBO), pageNum, pageSize);
    return baseConvert.convertPageDO2BO(pageList);
  }

  @Override
  public void delete(Long[] ids) {
    boolean b = baseService.deleteBatch(ids);
    log.info("delete BizCategory success, id: {}", ids);
    ConditionCheck.falseAndThrow(b, new CustomException("分类删除失败"));
  }

  @Override
  public void update(BizCategoryBO bizCategoryBO) {
    boolean b =
        baseService.updateOne(bizCategoryBO.getId(), baseConvert.convertBO2DO(bizCategoryBO));
    log.info("update BizCategory success, id: {}", bizCategoryBO.getId());
    ConditionCheck.falseAndThrow(b, new CustomException("分类更新失败"));
  }

  @Override
  public List<BizCategoryBO> list() {
    List<BizCategory> listByEntity = baseService.getListByEntity(null);
    return baseConvert.convertListDO2BO(listByEntity);
  }
}
