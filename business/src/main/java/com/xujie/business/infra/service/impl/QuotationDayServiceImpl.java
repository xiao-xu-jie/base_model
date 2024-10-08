package com.xujie.business.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.business.infra.DO.BizEggQuotationDay;
import com.xujie.business.infra.mapper.BizEggQuotationDayMapper;
import com.xujie.business.infra.service.QuotationDayService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 日行情服务实现
 *
 * @author Xujie
 * @since 2024/10/8 13:11
 */
@Slf4j
@Service
public class QuotationDayServiceImpl implements QuotationDayService {
  @Resource private BizEggQuotationDayMapper bizEggQuotationDayMapper;

  @Override
  public List<BizEggQuotationDay> getQuotationDayListByTime(
      String startTime, String endTime, Long typeId) {
    LambdaQueryWrapper<BizEggQuotationDay> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper
        .eq(BizEggQuotationDay::getTypeId, typeId)
        .between(BizEggQuotationDay::getDataTime, startTime, endTime);
    return bizEggQuotationDayMapper.selectList(queryWrapper);
  }
}
