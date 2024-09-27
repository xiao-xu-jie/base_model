package com.xujie.manager.controller;

import com.xujie.manager.DTO.req.BizSourceStationAddReqDTO;
import com.xujie.manager.DTO.req.BizSourceStationQueryReqDTO;
import com.xujie.manager.DTO.res.BizSourceStationQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.BizSourceStationBO;
import com.xujie.manager.domain.convert.BizSourceStationConvert;
import com.xujie.manager.domain.service.BizSourceStationDomainService;
import com.xujie.manager.infra.DO.BizSourceStation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizSourceStation)表控制层
 *
 * @author xujie
 * @since 2024-09-27 19:02:15
 */
@RestController
@RequestMapping("/bizSourceStation")
public class BizSourceStationController
    extends BaseController<
        BizSourceStationQueryReqDTO,
        BizSourceStationQueryResDTO,
        BizSourceStationAddReqDTO,
        BizSourceStationBO,
        BizSourceStation,
        BizSourceStationConvert,
        BizSourceStationDomainService> {

  public BizSourceStationController(
      BizSourceStationConvert baseConvert, BizSourceStationDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }

  /**
   * 获取货源列表
   *
   * @return 站点列表
   */
  @GetMapping("/list")
  public Result<List<BizSourceStationQueryResDTO>> list() {
    List<BizSourceStationBO> list = baseDomainService.list();
    return Result.ok(baseConvert.convertListBO2DTO(list));
  }
}
