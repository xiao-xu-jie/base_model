package com.xujie.manager.controller;

import com.xujie.manager.DTO.req.BizGoodAddReqDTO;
import com.xujie.manager.DTO.req.BizGoodQueryReqDTO;
import com.xujie.manager.DTO.res.BizGoodQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizGoodBO;
import com.xujie.manager.domain.convert.BizGoodConvert;
import com.xujie.manager.domain.service.BizGoodDomainService;
import com.xujie.manager.infra.DO.BizGood;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizGood)表控制层
 *
 * @author xujie
 * @since 2024-09-27 19:02:14
 */
@RestController
@RequestMapping("/bizGood")
public class BizGoodController
    extends BaseController<
        BizGoodQueryReqDTO,
        BizGoodQueryResDTO,
        BizGoodAddReqDTO,
        BizGoodBO,
        BizGood,
        BizGoodConvert,
        BizGoodDomainService> {

  public BizGoodController(BizGoodConvert baseConvert, BizGoodDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
