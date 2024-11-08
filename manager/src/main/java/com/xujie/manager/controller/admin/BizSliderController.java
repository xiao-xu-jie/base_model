package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizSliderAddReqDTO;
import com.xujie.manager.DTO.req.BizSliderQueryReqDTO;
import com.xujie.manager.DTO.res.BizSliderQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizSliderBO;
import com.xujie.manager.domain.convert.BizSliderConvert;
import com.xujie.manager.domain.service.BizSliderDomainService;
import com.xujie.manager.infra.DO.BizSlider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布页面轮播(BizSlider)表控制层
 *
 * @author xujie
 * @since 2024-11-07 09:07:49
 */
@RestController
@RequestMapping("/bizSlider")
public class BizSliderController
    extends BaseController<
        BizSliderQueryReqDTO,
        BizSliderQueryResDTO,
        BizSliderAddReqDTO,
        BizSliderBO,
        BizSlider,
        BizSliderConvert,
        BizSliderDomainService> {

  public BizSliderController(
      BizSliderConvert baseConvert, BizSliderDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
