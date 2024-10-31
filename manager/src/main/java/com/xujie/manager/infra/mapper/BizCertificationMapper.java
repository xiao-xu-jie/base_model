package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizCertification;
import org.apache.ibatis.annotations.Param;

public interface BizCertificationMapper extends BaseMapper<BizCertification> {
  Page<BizCertification> getPageList(
      @Param(Constants.WRAPPER) QueryWrapper<BizCertification> queryWrapper,
      Page<BizCertification> page);
}
