package com.xujie.business.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.domain.BO.BizEggQuotationBO;
import com.xujie.business.domain.BO.BizEggTypeBO;
import java.util.List;

public interface QuotationDomainService {
  List<BizEggTypeBO> listEggType();

  List<BizEggQuotationBO> listByEntity(BizEggQuotationBO entity);

  List<BizEggQuotationBO> listUserTodayQuotation();

  Page<BizEggQuotationBO> selectPage(BizEggQuotationBO entity, Integer pageNum, Integer pageSize);

  void submitTodayQuotation(BizEggQuotationBO entity);

  List<BizEggQuotationBO> getUserQuotationByDate(String date);

  void updateTodayQuotation(BizEggQuotationBO entity);

  String getUserPhone(Long id);
}
