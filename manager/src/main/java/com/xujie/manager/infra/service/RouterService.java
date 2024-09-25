package com.xujie.manager.infra.service;

import com.xujie.manager.common.base.service.BaseService;
import com.xujie.manager.infra.DO.SysRouters;

import java.util.List;

/**
 * 路由服务接口
 */
public interface RouterService extends BaseService<SysRouters> {

    List<SysRouters> getRouters();
    List<SysRouters> getAllTopRouters();
    List<SysRouters> getAllRouters();
}
