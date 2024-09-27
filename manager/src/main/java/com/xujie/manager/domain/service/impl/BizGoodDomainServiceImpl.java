package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.domain.BO.BizGoodBO;
import com.xujie.manager.domain.convert.BizGoodConvert;
import com.xujie.manager.domain.service.BizGoodDomainService;
import com.xujie.manager.infra.DO.BizGood;
import com.xujie.manager.infra.service.BizGoodService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (BizGood)表服务实现类
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@Slf4j
@Service
public class BizGoodDomainServiceImpl implements BizGoodDomainService {
  @Resource private BizGoodService baseService;
  @Resource private BizGoodConvert baseConvert;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void add(BizGoodBO bizGoodBO) {
    BizGood bizGood = baseConvert.convertBO2DO(bizGoodBO);
    Long l = baseService.addOne(bizGood);
    log.info("add BizGood success, id: {}", l);
    ConditionCheck.nullAndThrow(l, new CustomException("商品添加失败"));
  }

  @Override
  public Page<BizGoodBO> getPageList(BizGoodBO bizGoodBO, Integer pageNum, Integer pageSize) {
    Page<BizGood> pageList =
        baseService.getPageList(baseConvert.convertBO2DO(bizGoodBO), pageNum, pageSize);
    return baseConvert.convertPageDO2BO(pageList);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void delete(Long[] ids) {
    boolean b = baseService.deleteBatch(ids);
    log.info("delete BizGood success, id: {}", ids);
    ConditionCheck.falseAndThrow(b, new CustomException("商品删除失败"));
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void update(BizGoodBO bizGoodBO) {
    boolean b = baseService.updateOne(bizGoodBO.getId(), baseConvert.convertBO2DO(bizGoodBO));
    log.info("update BizGood success, id: {}", bizGoodBO.getId());
    ConditionCheck.falseAndThrow(b, new CustomException("商品更新失败"));
  }
}
