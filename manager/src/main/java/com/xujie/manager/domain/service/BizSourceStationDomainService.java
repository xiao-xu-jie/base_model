package com.xujie.manager.domain.service;

import com.xujie.manager.common.base.service.BaseDomainService;
import com.xujie.manager.domain.BO.BizSourceStationBO;
import java.util.List;

/**
 * (BizSourceStation)表服务接口
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
public interface BizSourceStationDomainService extends BaseDomainService<BizSourceStationBO> {
  List<BizSourceStationBO> list();
}
