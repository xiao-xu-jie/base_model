package com.xujie.manager.infra.service;

import com.xujie.manager.infra.DO.SysRouters;

import java.util.List;

/**
 * 路由服务接口
 */
public interface RouterService {

    List<SysRouters> getRouters();

    boolean addRouter(SysRouters router);

    boolean deleteRouter(Long id);

    boolean updateRouter(Long id, SysRouters router);
}
