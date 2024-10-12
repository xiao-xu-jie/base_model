package com.xujie.business.infra.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizUserVip;

public interface BizUserVipMapper extends BaseMapper<BizUserVip> {
	int updateByUserId(@Param("updated")BizUserVip updated,@Param("userId")Long userId);


}