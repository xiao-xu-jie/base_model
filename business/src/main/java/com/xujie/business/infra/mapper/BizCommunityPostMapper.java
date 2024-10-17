package com.xujie.business.infra.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xujie.business.common.enums.ShowStatusEnum;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.business.infra.DO.BizCommunityPost;

public interface BizCommunityPostMapper extends BaseMapper<BizCommunityPost> {
	List<BizCommunityPost> selectByAll(BizCommunityPost bizCommunityPost);


}
