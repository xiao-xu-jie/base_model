package com.xujie.manager.controller;

import com.xujie.manager.DTO.req.BizOrderAddReqDTO;
import com.xujie.manager.DTO.req.BizOrderQueryReqDTO;
import com.xujie.manager.DTO.res.BizOrderQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizOrderBO;
import com.xujie.manager.domain.convert.BizOrderConvert;
import com.xujie.manager.domain.service.BizOrderDomainService;
import com.xujie.manager.infra.DO.BizOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizOrder)表控制层
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@RestController
@RequestMapping("/bizOrder")
public class BizOrderController
    extends BaseController<
        BizOrderQueryReqDTO,
        BizOrderQueryResDTO,
        BizOrderAddReqDTO,
        BizOrderBO,
        BizOrder,
        BizOrderConvert,
        BizOrderDomainService> {

  public BizOrderController(BizOrderConvert baseConvert, BizOrderDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
