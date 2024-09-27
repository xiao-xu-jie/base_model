package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizOrderBO;
import com.xujie.manager.domain.service.BizOrderDomainService;
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
  @Override
  public void add(BizOrderBO bizOrderBO) {}

  @Override
  public Page<BizOrderBO> getPageList(BizOrderBO bizOrderBO, Integer pageNum, Integer pageSize) {
    return null;
  }

  @Override
  public void delete(Long[] ids) {}

  @Override
  public void update(BizOrderBO bizOrderBO) {}
}
