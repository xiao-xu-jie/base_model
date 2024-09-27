package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizOrder;
import com.xujie.manager.infra.service.BizOrderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现
 *
 * @author Xujie
 * @since 2024/9/27 19:11
 */
@Slf4j
@Service
public class BizOrderServiceImpl implements BizOrderService {
  @Override
  public Long addOne(BizOrder baseDO) {
    return 0L;
  }

  @Override
  public BizOrder getOneByEntity(BizOrder baseDO) {
    return null;
  }

  @Override
  public List<BizOrder> getListByEntity(BizOrder baseDO) {
    return List.of();
  }

  @Override
  public boolean deleteOne(Long id) {
    return false;
  }

  @Override
  public boolean updateOne(Long id, BizOrder baseDO) {
    return false;
  }

  @Override
  public Page<BizOrder> getPageList(BizOrder baseDO, Integer pageNum, Integer pageSize) {
    return null;
  }

  @Override
  public boolean deleteBatch(Long[] ids) {
    return false;
  }
}
