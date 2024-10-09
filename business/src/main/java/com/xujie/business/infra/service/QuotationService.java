package com.xujie.business.infra.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggType;
import java.util.List;

public interface QuotationService {
  /**
   * 获取蛋种类列表
   *
   * @return 蛋种类列表
   */
  List<BizEggType> listEggType();

  /**
   * 获取报价列表
   *
   * @param entity 查询条件
   * @return 报价列表
   */
  List<BizEggQuotation> listByEntity(BizEggQuotation entity);

  /**
   * 分页查询报价列表
   *
   * @param entity
   * @param pageNum
   * @param pageSize
   * @return
   */
  Page<BizEggQuotation> selectPage(BizEggQuotation entity, Integer pageNum, Integer pageSize);
}
