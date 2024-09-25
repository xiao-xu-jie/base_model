package com.xujie.manager.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.SysOperLog;
import com.xujie.manager.infra.mapper.SysOperLogMapper;
import com.xujie.manager.infra.service.OperLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志服务实现类
 * @author Xujie
 * @since 2024/9/25 16:12
 **/

@Slf4j
@Service
public class OperLogServiceImpl implements OperLogService {

    @Resource
    private SysOperLogMapper baseMapper;
    @Override
    public Long addOne(SysOperLog baseDO) {
        try {
            baseMapper.insert(baseDO);
        } catch (Exception e) {
            log.error("添加操作日志失败", e);
            return null;
        }
        return baseDO.getId();
    }

    @Override
    public SysOperLog getOneByEntity(SysOperLog baseDO) {
        return getListByEntity(baseDO).stream().findFirst().orElse(null);
    }

    @Override
    public List<SysOperLog> getListByEntity(SysOperLog baseDO) {
        LambdaQueryWrapper<SysOperLog> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .eq(baseDO.getId() != null, SysOperLog::getId, baseDO.getId())
                .eq(baseDO.getLogDesc() != null, SysOperLog::getLogDesc, baseDO.getLogDesc());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean deleteOne(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateOne(Long id, SysOperLog baseDO) {
        return baseMapper.updateById(baseDO,id) > 0;
    }

    @Override
    public Page<SysOperLog> getPageList(SysOperLog baseDO, Integer pageNum, Integer pageSize) {
        Page<SysOperLog> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SysOperLog> queryWrapper = Wrappers.lambdaQuery(baseDO);
        queryWrapper
                .eq(baseDO.getId() != null, SysOperLog::getId, baseDO.getId())
                .eq(baseDO.getLogDesc() != null, SysOperLog::getLogDesc, baseDO.getLogDesc());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        return baseMapper.deleteByIds(List.of(ids)) > 0;
    }
}
