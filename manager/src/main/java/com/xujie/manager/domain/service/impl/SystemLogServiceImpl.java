package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.SystemLogBO;
import com.xujie.manager.domain.service.SystemLogDomainService;
import org.springframework.stereotype.Service;

/**
 * (SysSystemLog)表服务实现类
 *
 * @author xujie
 * @since 2024-09-22 10:18:07
 */
@Service("sysSystemLogService")
public class SystemLogServiceImpl implements SystemLogDomainService {

    @Override
    public void add(SystemLogBO systemLogBO) {

    }

    @Override
    public Page<SystemLogBO> getPageList(SystemLogBO systemLogBO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public void update(SystemLogBO systemLogBO) {

    }
}

