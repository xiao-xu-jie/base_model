package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.common.utils.ConditionCheck;
import com.xujie.manager.domain.BO.OperLogBO;
import com.xujie.manager.domain.convert.OperLogConvert;
import com.xujie.manager.domain.service.OperLogDomainService;
import com.xujie.manager.infra.DO.SysOperLog;
import com.xujie.manager.infra.service.OperLogService;
import jakarta.annotation.Resource;
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
    @Resource
    private OperLogService baseService;
    @Resource
    private OperLogConvert baseConvert;

    @Override
    public void add(OperLogBO operLogBO) {

        Long l = baseService.addOne(baseConvert.convertBO2DO(operLogBO));
        ConditionCheck.nullAndThrow(l, new CustomException("添加操作日志失败"));
    }

    @Override
    public Page<OperLogBO> getPageList(OperLogBO operLogBO, Integer pageNum, Integer pageSize) {
        SysOperLog sysOperLog = baseConvert.convertBO2DO(operLogBO);
        return baseConvert.convertPageDO2BO(baseService.getPageList(sysOperLog, pageNum, pageSize));
    }

    @Override
    public void delete(Long[] ids) {
        boolean b = baseService.deleteBatch(ids);
        ConditionCheck.falseAndThrow(b, new CustomException("删除操作日志失败"));
    }

    @Override
    public void update(OperLogBO operLogBO) {
        boolean b = baseService.updateOne(operLogBO.getId(), baseConvert.convertBO2DO(operLogBO));
        ConditionCheck.falseAndThrow(b, new CustomException("更新操作日志失败"));
    }
}

