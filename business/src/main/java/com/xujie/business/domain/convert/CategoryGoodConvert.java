package com.xujie.business.domain.convert;

import com.xujie.business.DTO.res.CategoryGoodQueryResDTO;
import com.xujie.business.domain.BO.CategoryBO;
import com.xujie.business.domain.BO.CategoryGoodBO;
import com.xujie.business.domain.BO.GoodBO;
import com.xujie.business.infra.DO.BizCategory;
import com.xujie.business.infra.DO.BizGood;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryGoodConvert {
    List<CategoryBO> convertCategoryListDO2BO(List<BizCategory> bizCategoryList);
    List<GoodBO> convertGoodListDO2BO(List<BizGood> bizCategoryList);

    List<CategoryGoodQueryResDTO> convertCGBO2DTO(List<CategoryGoodBO> categoryListByEntity);
}
