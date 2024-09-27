package com.xujie.business.domain.service;


import com.xujie.business.domain.BO.CategoryGoodBO;

import java.util.List;

public interface CategoryGoodDomainService {
    List<CategoryGoodBO> getCategoryListByEntity(CategoryGoodBO categoryGoodBO);
}
