package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xujie.manager.infra.DO.BizUserVip;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizUserVipMapper extends BaseMapper<BizUserVip> {
  List<BizUserVip> getUserVipList(@Param(Constants.WRAPPER) QueryWrapper<BizUserVip> queryWrapper);
}
