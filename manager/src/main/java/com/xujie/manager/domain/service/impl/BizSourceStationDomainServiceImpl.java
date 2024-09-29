package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.exception.CustomException;
import com.xujie.manager.domain.BO.BizSourceStationBO;
import com.xujie.manager.domain.convert.BizSourceStationConvert;
import com.xujie.manager.domain.service.BizSourceStationDomainService;
import com.xujie.manager.infra.DO.BizSourceStation;
import com.xujie.manager.infra.service.BizSourceService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizSourceStation)表服务实现类
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@Slf4j
@Service
public class BizSourceStationDomainServiceImpl implements BizSourceStationDomainService {
  @Resource private BizSourceService baseService;
  @Resource private BizSourceStationConvert baseConvert;

  @Override
  public void add(BizSourceStationBO bizSourceStationBO) {
    BizSourceStation bizSourceStation = baseConvert.convertBO2DO(bizSourceStationBO);
    Long l = baseService.addOne(bizSourceStation);
    log.info("add BizSourceStation success, id: {}", l);
    ConditionCheck.nullAndThrow(l, new CustomException("货源添加失败"));
  }

  @Override
  public Page<BizSourceStationBO> getPageList(
      BizSourceStationBO bizSourceStationBO, Integer pageNum, Integer pageSize) {
    Page<BizSourceStation> pageList =
        baseService.getPageList(baseConvert.convertBO2DO(bizSourceStationBO), pageNum, pageSize);
    return baseConvert.convertPageDO2BO(pageList);
  }

  @Override
  public void delete(Long[] ids) {
    boolean b = baseService.deleteBatch(ids);
    log.info("delete BizSourceStation success, id: {}", ids);
    ConditionCheck.falseAndThrow(b, new CustomException("货源删除失败"));
  }

  @Override
  public void update(BizSourceStationBO bizSourceStationBO) {
    boolean b =
        baseService.updateOne(
            bizSourceStationBO.getId(), baseConvert.convertBO2DO(bizSourceStationBO));
    log.info("update BizSourceStation success, id: {}", bizSourceStationBO.getId());
    ConditionCheck.falseAndThrow(b, new CustomException("货源更新失败"));
  }

  @Override
  public List<BizSourceStationBO> list() {
    return baseService.getListByEntity(BizSourceStation.builder().isDelete(0).build()).stream()
        .map(baseConvert::convertDO2BO)
        .toList();
  }
}
