package com.xujie.business.infra.service.impl;

import com.xujie.business.infra.DO.BizCategory;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.mapper.BizCategoryMapper;
import com.xujie.business.infra.mapper.BizGoodMapper;
import com.xujie.business.infra.service.CategoryGoodService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/27 08:30
 **/

@Slf4j
@Service
public class CategoryGoodServiceImpl implements CategoryGoodService {
    @Resource
    private BizCategoryMapper bizCategoryMapper;
    @Resource
    private BizGoodMapper bizGoodMapper;
    @Override
    public List<BizCategory> getCategoryListByEntity(BizCategory bizCategory) {
        return bizCategoryMapper.getByAll(bizCategory);
    }

    @Override
    public List<BizGood> getGoodListByEntity(BizGood bizGood) {
        return bizGoodMapper.getByAll(bizGood);
    }
}
