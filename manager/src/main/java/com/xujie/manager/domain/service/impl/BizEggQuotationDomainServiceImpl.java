package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizEggQuotationBO;
import com.xujie.manager.domain.convert.BizEggQuotationConvert;
import com.xujie.manager.domain.service.BizEggQuotationDomainService;
import com.xujie.manager.infra.DO.BizEggQuotation;
import com.xujie.manager.infra.service.BizQuotationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizEggQuotation)表服务实现类
 *
 * @author xujie
 * @since 2024-11-01 23:29:20
 */
@Slf4j
@Service
public class BizEggQuotationDomainServiceImpl implements BizEggQuotationDomainService {
  @Resource private BizQuotationService bizQuotationService;
  @Resource private BizEggQuotationConvert bizEggQuotationConvert;

  @Override
  public void add(BizEggQuotationBO bizEggQuotationBO) {
    bizQuotationService.addOne(bizEggQuotationConvert.convertBO2DO(bizEggQuotationBO));
  }

  @Override
  public Page<BizEggQuotationBO> getPageList(
      BizEggQuotationBO bizEggQuotationBO, Integer pageNum, Integer pageSize) {
    BizEggQuotation bizEggQuotation = bizEggQuotationConvert.convertBO2DO(bizEggQuotationBO);
    Page<BizEggQuotation> page =
        bizQuotationService.getPageList(bizEggQuotation, pageNum, pageSize);
    return bizEggQuotationConvert.convertPageDO2BO(page);
  }

  @Override
  public void delete(Long[] ids) {
    bizQuotationService.deleteBatch(ids);
  }

  @Override
  public void update(BizEggQuotationBO bizEggQuotationBO) {
    bizQuotationService.updateOne(
        bizEggQuotationBO.getId(), bizEggQuotationConvert.convertBO2DO(bizEggQuotationBO));
  }
}
