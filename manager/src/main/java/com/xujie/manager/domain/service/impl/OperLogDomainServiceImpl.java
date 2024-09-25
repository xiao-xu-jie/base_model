package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.OperLogBO;
import com.xujie.manager.domain.service.OperLogDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (OperLog)表服务实现类
 *
 * @author xujie
 * @since 2024-09-25 16:00:23
 */
@Slf4j
@Service
public class OperLogDomainServiceImpl implements OperLogDomainService {

    @Override
    public void add(OperLogBO operLogBO) {

    }

    @Override
    public Page<OperLogBO> getPageList(OperLogBO operLogBO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public void update(OperLogBO operLogBO) {

    }
}

