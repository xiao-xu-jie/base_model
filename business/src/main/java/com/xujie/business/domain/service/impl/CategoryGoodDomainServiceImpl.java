package com.xujie.business.domain.service.impl;

import com.xujie.business.domain.BO.CategoryBO;
import com.xujie.business.domain.BO.CategoryGoodBO;
import com.xujie.business.domain.BO.GoodBO;
import com.xujie.business.domain.convert.CategoryGoodConvert;
import com.xujie.business.domain.service.CategoryGoodDomainService;
import com.xujie.business.infra.DO.BizCategory;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.service.CategoryGoodService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Xujie
 * @since 2024/9/27 08:28
 **/
@Slf4j
@Service
public class CategoryGoodDomainServiceImpl implements CategoryGoodDomainService {
    @Resource
    private CategoryGoodService categoryGoodService;
    @Resource
    private CategoryGoodConvert categoryGoodConvert;
    @Override
    public List<CategoryGoodBO> getCategoryListByEntity(CategoryGoodBO categoryGoodBO) {
        List<BizCategory> categoryListByEntity = categoryGoodService.getCategoryListByEntity(null);
        List<BizGood> goodListByEntity = categoryGoodService.getGoodListByEntity(null);
        return mergeEntity(categoryGoodConvert.convertCategoryListDO2BO(categoryListByEntity),
                categoryGoodConvert.convertGoodListDO2BO(goodListByEntity));
    }

    private List<CategoryGoodBO> mergeEntity(List<CategoryBO> categoryListByEntity, List<GoodBO> goodListByEntity) {
        List<CategoryGoodBO> list = categoryListByEntity.stream()
                .map(item -> CategoryGoodBO.builder()
                        .categoryName(item.getCategoryName())
                        .categoryDesc(item.getCategoryDesc())
                        .categoryStatus(item.getCategoryStatus())
                        .id(item.getId())
                        .goodList(goodListByEntity.stream()
                                .filter(good -> good.getCategoryId().equals(item.getId()))
                                .sorted(Comparator.comparing(GoodBO::getGoodRank))
                                .toList())
                        .build()).toList();
        return list;
    }
}
