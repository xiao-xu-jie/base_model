package com.xujie.business.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.convert.QuotationConvert;
import com.xujie.business.domain.BO.BizEggQuotationBO;
import com.xujie.business.domain.BO.BizEggTypeBO;
import com.xujie.business.domain.service.QuotationDomainService;
import com.xujie.business.domain.service.UserDomainService;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggType;
import com.xujie.business.infra.service.QuotationService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 报价领域服务实现
 *
 * @author Xujie
 * @since 2024/10/6 13:02
 */
@Slf4j
@Service
public class QuotationDomainServiceImpl implements QuotationDomainService {
  @Resource private QuotationService quotationService;
  @Resource private UserDomainService userDomainService;
  @Resource private QuotationConvert quotationConvert;

  @Resource(name = "asyncExecutor")
  private ThreadPoolTaskExecutor asyncExecutor;

  @Override
  public List<BizEggTypeBO> listEggType() {
    List<BizEggType> bizEggTypes = quotationService.listEggType();
    return quotationConvert.convertEggTypeDOList2BOList(bizEggTypes);
  }

  @Override
  public List<BizEggQuotationBO> listByEntity(BizEggQuotationBO entity) {
    List<BizEggQuotation> bizEggQuotations =
        quotationService.listByEntity(quotationConvert.convertEggQuotationBO2DO(entity));
    return quotationConvert.convertEggQuotationDOList2BOList(bizEggQuotations);
  }

  @Override
  public List<BizEggQuotationBO> listUserTodayQuotation() {
    Long userId = StpUtil.getLoginIdAsLong();
    ConditionCheck.nullAndThrow(userId, new CustomException("用户未登录"));

    log.info("[报价][获取用户今日报价]时间：{}", DateUtil.getTodayString());
    BizEggQuotation build =
        BizEggQuotation.builder().userId(userId).dataDate(DateUtil.getTodayString()).build();
    List<BizEggQuotation> bizEggQuotations = quotationService.listByEntity(build);
    return quotationConvert.convertEggQuotationDOList2BOList(bizEggQuotations);
  }

  @Override
  public Page<BizEggQuotationBO> selectPage(
      BizEggQuotationBO entity, Integer pageNum, Integer pageSize) {
    Page<BizEggQuotation> bizEggQuotationPage =
        quotationService.selectPage(
            quotationConvert.convertEggQuotationBO2DO(entity), pageNum, pageSize);
    return quotationConvert.convertEggQuotationPageDO2BO(bizEggQuotationPage);
  }
}
