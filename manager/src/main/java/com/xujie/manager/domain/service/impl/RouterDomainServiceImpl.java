package com.xujie.manager.domain.service.impl;

import com.xujie.manager.DTO.res.Meta;
import com.xujie.manager.common.utils.RouterUtil;
import com.xujie.manager.domain.BO.RouterBO;
import com.xujie.manager.domain.convert.RouterConvert;
import com.xujie.manager.domain.service.RouterDomainService;
import com.xujie.manager.infra.service.RouterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 路由领域服务实现类
 *
 * @author Xujie
 * @since 2024/9/17 22:32
 **/

@Slf4j
@Service
public class RouterDomainServiceImpl implements RouterDomainService {
    @Resource
    private RouterConvert routerConvert;
    @Resource
    private RouterService routerService;


    @Override
    public List<RouterBO> getRouters() {
        List<RouterBO> routerBOS = routerConvert.convertDo2Bo(routerService.getRouters());
        routerBOS.forEach(item -> {
            item.setMeta(new Meta(item.getTitle(), item.getIcon(), item.getRank()));
//            item.setChildren(Lists.newArrayList());
        });
        // 递归设置子节点
        routerBOS = RouterUtil.setChildren(routerBOS);
        return routerBOS;
    }
}
