package com.xujie.manager.common.utils;

import com.xujie.manager.domain.BO.RouterBO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/17 22:36
 **/


public class RouterUtil {
    public static List<RouterBO> setChildren(List<RouterBO> routerBOS) {

        // 递归设置子节点
        for (RouterBO routerBO : routerBOS) {
            List<RouterBO> children = getChildren(routerBO.getId(), routerBOS);
            if (!children.isEmpty()) {
                routerBO.setChildren(children);
            }
        }
        return routerBOS.stream().filter(routerBO -> routerBO.getParentId() == null).toList();
    }

    private static List<RouterBO> getChildren(Long id, List<RouterBO> routerBOS) {
        List<RouterBO> children = new ArrayList<>();
        for (RouterBO routerBO : routerBOS) {
            if (routerBO.getParentId() != null && routerBO.getParentId().equals(id)) {
                children.add(routerBO);
            }
        }
        for (RouterBO child : children) {
            List<RouterBO> children1 = getChildren(child.getId(), routerBOS);
            if (!children1.isEmpty()) {
                child.setChildren(children1);
            }
        }
        return children;
    }

}
