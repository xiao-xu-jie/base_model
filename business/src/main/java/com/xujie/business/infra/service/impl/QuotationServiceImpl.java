package com.xujie.business.infra.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggType;
import com.xujie.business.infra.mapper.BizEggQuotationMapper;
import com.xujie.business.infra.mapper.BizEggTypeMapper;
import com.xujie.business.infra.service.QuotationService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 报价服务实现
 *
 * @author Xujie
 * @since 2024/10/6 13:00
 */
@Slf4j
@Service
public class QuotationServiceImpl implements QuotationService {
  @Resource private BizEggTypeMapper bizEggTypeMapper;
  @Resource private BizEggQuotationMapper bizEggQuotationMapper;

  /**
   * 获取蛋种类列表
   *
   * @return 蛋种类列表
   */
  @Override
  public List<BizEggType> listEggType() {
    return bizEggTypeMapper.selectList(null);
  }

  @Override
  public List<BizEggQuotation> listByEntity(BizEggQuotation entity) {
    return bizEggQuotationMapper.getByAll(entity);
  }

  @Override
  public BizEggQuotation getByEntity(BizEggQuotation entity) {
    return listByEntity(entity).stream().findFirst().orElse(null);
  }

  @Override
  public Page<BizEggQuotation> selectPage(
      BizEggQuotation entity, Integer pageNum, Integer pageSize) {
    QueryWrapper<BizEggQuotation> wrapper = new QueryWrapper<>(entity);
    List<String> weekDateList = DateUtil.getWeekDateTimeList();
    wrapper.eq(ObjectUtil.isNotNull(entity.getEggTypeId()), "egg_type_id", entity.getEggTypeId());
    wrapper.eq(
        ObjectUtil.isNotNull(entity.getQuotationType()),
        "quotation_type",
        entity.getQuotationType());
    wrapper.in("data_date", weekDateList);
    wrapper.like(
        StringUtils.isNotBlank(entity.getQuotationLocation()),
        "quotation_location",
        entity.getQuotationLocation());
    wrapper.groupBy("user_id");
    wrapper.orderByDesc("data_date");
    return bizEggQuotationMapper.getByPage(
        wrapper, new Page<>(pageNum, pageSize), StpUtil.getLoginIdAsLong());
  }

  @Override
  public int add(BizEggQuotation entity) {
    return bizEggQuotationMapper.insert(entity);
  }
}
