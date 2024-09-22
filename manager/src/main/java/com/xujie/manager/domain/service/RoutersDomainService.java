package com.xujie.manager.domain.service;

import com.xujie.manager.common.base.service.BaseDomainService;
import com.xujie.manager.domain.BO.RoutersBO;

import java.util.List;

/**
 * (SysRouters)表服务接口
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
public interface RoutersDomainService extends BaseDomainService<RoutersBO> {

    List<RoutersBO> getRouters();

    List<RoutersBO> getChildren(Long parentId);

    List<RoutersBO> getTopRouters();
}

