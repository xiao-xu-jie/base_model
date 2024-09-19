package com.xujie.manager.infra.service.impl;

import com.xujie.manager.infra.DO.SysRouters;
import com.xujie.manager.infra.mapper.SysRoutersMapper;
import com.xujie.manager.infra.service.RouterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 路由服务实现类
 *
 * @author Xujie
 * @since 2024/9/17 22:27
 **/

@Slf4j
@Service
public class RouterServiceImpl implements RouterService {
    @Resource
    private SysRoutersMapper baseMapper;


    /**
     * 获取路由列表
     *
     * @return 路由列表
     */
    @Override
    public List<SysRouters> getRouters() {
        List<SysRouters> sysRouters = baseMapper.selectList(null);

        return sysRouters;
    }

    @Override
    public boolean addRouter(SysRouters router) {
        try {
            baseMapper.insert(router);
        } catch (Exception e) {
            log.error("添加路由失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean deleteRouter(Long id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            log.error("删除路由失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean updateRouter(Long id, SysRouters router) {
        try {
            router.setId(id);
            baseMapper.updateById(router);
        } catch (Exception e) {
            log.error("更新路由失败：{}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
