package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.DTO.res.Meta;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.common.utils.RouterUtil;
import com.xujie.manager.domain.BO.RoutersBO;
import com.xujie.manager.domain.convert.RoutersConvert;
import com.xujie.manager.domain.service.RoutersDomainService;
import com.xujie.manager.infra.DO.SysRouters;
import com.xujie.manager.infra.service.RouterService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysRouters)表服务实现类
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@Slf4j
@Service
public class RoutersDomainServiceImpl implements RoutersDomainService {
    @Resource
    private RoutersConvert routerConvert;
    @Resource
    private RouterService routerService;

    @Override
    public void add(RoutersBO routersBO) {
        Long id = routerService.addOne(routerConvert.convertBO2DO(routersBO));
        ConditionCheck.nullAndThrow(id, new CustomException("添加路由失败"));
    }

    @Override
    public Page<RoutersBO> getPageList(RoutersBO routersBO, Integer pageNum, Integer pageSize) {
        SysRouters sysRouters = routerConvert.convertBO2DO(routersBO);
        Page<SysRouters> page = routerService.getPageList(sysRouters, pageNum, pageSize);
        return routerConvert.convertPageDO2BO(page);
    }

    @Override
    public void delete(Long[] ids) {
        boolean flag = routerService.deleteBatch(ids);
        ConditionCheck.falseAndThrow(flag, new CustomException("删除路由失败"));
    }

    @Override
    public void update(RoutersBO routersBO) {
        boolean b = routerService.updateOne(routersBO.getId(), routerConvert.convertBO2DO(routersBO));
        ConditionCheck.falseAndThrow(b, new CustomException("更新路由失败"));
    }

    @Override
    public List<RoutersBO> getRouters() {
        List<RoutersBO> routerBOS = routerConvert.convertListDO2BO(routerService.getRouters());
        routerBOS.forEach(item -> {
            item.setMeta(new Meta(item.getTitle(), item.getIcon(), item.getRank(),item.getShowlink()));
//            item.setChildren(Lists.newArrayList());
        });
        // 递归设置子节点
        routerBOS = RouterUtil.setChildren(routerBOS);
        return routerBOS;
    }

    @Override
    public List<RoutersBO> getChildren(Long parentId) {
        List<RoutersBO> routerBOS = routerConvert
                .convertListDO2BO(routerService.getListByEntity(SysRouters.builder().parentId(parentId).build()));
        return routerBOS;
    }

    @Override
    public List<RoutersBO> getTopRouters() {
        List<RoutersBO> routerBOS = routerConvert
                .convertListDO2BO(routerService.getListByEntity(SysRouters.builder().parentId(0L).build()));
        return routerBOS;
    }
}

