package com.xujie.manager.infra.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.manager.infra.DO.BizUser;

public interface BizUserMapper extends BaseMapper<BizUser> {
	List<BizUser> findByAll(BizUser bizUser);


}