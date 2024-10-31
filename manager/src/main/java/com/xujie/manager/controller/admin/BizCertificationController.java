package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizCertificationAddReqDTO;
import com.xujie.manager.DTO.req.BizCertificationQueryReqDTO;
import com.xujie.manager.DTO.res.BizCertificationQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizCertificationBO;
import com.xujie.manager.domain.convert.BizCertificationConvert;
import com.xujie.manager.domain.service.BizCertificationDomainService;
import com.xujie.manager.infra.DO.BizCertification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizCertification)表控制层
 *
 * @author xujie
 * @since 2024-10-31 11:46:35
 */
@RestController
@RequestMapping("/bizCertification")
public class BizCertificationController
    extends BaseController<
        BizCertificationQueryReqDTO,
        BizCertificationQueryResDTO,
        BizCertificationAddReqDTO,
        BizCertificationBO,
        BizCertification,
        BizCertificationConvert,
        BizCertificationDomainService> {

  public BizCertificationController(
      BizCertificationConvert baseConvert, BizCertificationDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
