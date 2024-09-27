package com.xujie.business.infra.mapper;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizCategory;

public interface BizCategoryMapper extends BaseMapper<BizCategory> {
    List<BizCategory> getByAll(BizCategory bizCategory);




}