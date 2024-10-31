package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.manager.infra.DO.BizEggQuotation;
import com.xujie.manager.infra.mapper.BizEggQuotationMapper;
import com.xujie.manager.infra.service.BizQuotationService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizQuotation)表服务实现类
 *
 * @author Xujie
 * @since 2024/10/31 10:51
 */
@Slf4j
@Service
public class BizQuotationServiceImpl implements BizQuotationService {
  @Resource private BizEggQuotationMapper bizEggQuotationMapper;

  @Override
  public List<BizEggQuotation> getQuotationListByUserIds(List<Long> userIds) {
    LambdaQueryWrapper<BizEggQuotation> lambdaQuery = new LambdaQueryWrapper<>();
    lambdaQuery.orderByDesc(BizEggQuotation::getDataDate);
    lambdaQuery.in(BizEggQuotation::getUserId, userIds);
    return bizEggQuotationMapper.selectList(lambdaQuery);
  }
}
