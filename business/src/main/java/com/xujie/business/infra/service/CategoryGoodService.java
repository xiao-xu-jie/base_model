package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizCategory;
import com.xujie.business.infra.DO.BizGood;

import java.util.List;

public interface CategoryGoodService {
    List<BizCategory> getCategoryListByEntity(BizCategory bizCategory);
    List<BizGood> getGoodListByEntity(BizGood bizGood);
}
