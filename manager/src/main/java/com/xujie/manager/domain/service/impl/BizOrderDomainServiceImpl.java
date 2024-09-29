package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.domain.BO.BizOrderBO;
import com.xujie.manager.domain.convert.BizOrderConvert;
import com.xujie.manager.domain.service.BizOrderDomainService;
import com.xujie.manager.infra.DO.BizOrder;
import com.xujie.manager.infra.service.BizOrderService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizOrder)表服务实现类
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@Slf4j
@Service
public class BizOrderDomainServiceImpl implements BizOrderDomainService {

  @Resource private BizOrderService baseService;
  @Resource private BizOrderConvert baseConvert;

  @Override
  public void add(BizOrderBO bizOrderBO) {
    Long l = baseService.addOne(baseConvert.convertBO2DO(bizOrderBO));
    ConditionCheck.nullAndThrow(l, new CustomException("添加失败"));
  }

  @Override
  public Page<BizOrderBO> getPageList(BizOrderBO bizOrderBO, Integer pageNum, Integer pageSize) {
    Page<BizOrder> pageList =
        baseService.getPageList(baseConvert.convertBO2DO(bizOrderBO), pageNum, pageSize);
    return baseConvert.convertPageDO2BO(pageList);
  }

  @Override
  public void delete(Long[] ids) {
    boolean b = baseService.deleteBatch(ids);
    ConditionCheck.falseAndThrow(!b, new CustomException("删除失败"));
  }

  @Override
  public void update(BizOrderBO bizOrderBO) {
    boolean b = baseService.updateOne(bizOrderBO.getId(), baseConvert.convertBO2DO(bizOrderBO));
    ConditionCheck.falseAndThrow(!b, new CustomException("更新失败"));
  }
}
