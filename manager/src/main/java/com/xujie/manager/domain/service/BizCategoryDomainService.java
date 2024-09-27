package com.xujie.manager.domain.service;

import com.xujie.manager.common.base.service.BaseDomainService;
import com.xujie.manager.domain.BO.BizCategoryBO;
import java.util.List;

/**
 * (BizCategory)表服务接口
 *
 * @author xujie
 * @since 2024-09-27 19:02:12
 */
public interface BizCategoryDomainService extends BaseDomainService<BizCategoryBO> {
  List<BizCategoryBO> list();
}
