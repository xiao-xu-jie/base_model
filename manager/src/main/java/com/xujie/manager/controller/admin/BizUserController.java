package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizUserAddReqDTO;
import com.xujie.manager.DTO.req.BizUserQueryReqDTO;
import com.xujie.manager.DTO.res.BizUserQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizUserBO;
import com.xujie.manager.domain.convert.BizUserConvert;
import com.xujie.manager.domain.service.BizUserDomainService;
import com.xujie.manager.infra.DO.BizUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizUser)表控制层
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@RestController
@RequestMapping("/bizUser")
public class BizUserController
    extends BaseController<
        BizUserQueryReqDTO,
        BizUserQueryResDTO,
        BizUserAddReqDTO,
        BizUserBO,
        BizUser,
        BizUserConvert,
        BizUserDomainService> {

  public BizUserController(BizUserConvert baseConvert, BizUserDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
