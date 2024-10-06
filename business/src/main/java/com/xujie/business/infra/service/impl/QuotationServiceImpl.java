package com.xujie.business.infra.service.impl;

import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggType;
import com.xujie.business.infra.mapper.BizEggQuotationMapper;
import com.xujie.business.infra.mapper.BizEggTypeMapper;
import com.xujie.business.infra.service.QuotationService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 报价服务实现
 *
 * @author Xujie
 * @since 2024/10/6 13:00
 */
@Slf4j
@Service
public class QuotationServiceImpl implements QuotationService {
  @Resource private BizEggTypeMapper bizEggTypeMapper;
  @Resource private BizEggQuotationMapper bizEggQuotationMapper;

  /**
   * 获取蛋种类列表
   *
   * @return 蛋种类列表
   */
  @Override
  public List<BizEggType> listEggType() {
    return bizEggTypeMapper.selectList(null);
  }

  @Override
  public List<BizEggQuotation> listByEntity(BizEggQuotation entity) {
    return bizEggQuotationMapper.getByAll(entity);
  }
}
