package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizEggQuotationAddReqDTO;
import com.xujie.manager.DTO.req.BizEggQuotationQueryReqDTO;
import com.xujie.manager.DTO.res.BizEggQuotationQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizEggQuotationBO;
import com.xujie.manager.domain.convert.BizEggQuotationConvert;
import com.xujie.manager.domain.service.BizEggQuotationDomainService;
import com.xujie.manager.infra.DO.BizEggQuotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizEggQuotation)表控制层
 *
 * @author xujie
 * @since 2024-11-01 23:29:20
 */
@RestController
@RequestMapping("/bizEggQuotation")
public class BizEggQuotationController
    extends BaseController<
        BizEggQuotationQueryReqDTO,
        BizEggQuotationQueryResDTO,
        BizEggQuotationAddReqDTO,
        BizEggQuotationBO,
        BizEggQuotation,
        BizEggQuotationConvert,
        BizEggQuotationDomainService> {

  public BizEggQuotationController(
      BizEggQuotationConvert baseConvert, BizEggQuotationDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
