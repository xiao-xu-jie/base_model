package com.xujie.manager.controller;

import com.xujie.manager.DTO.req.BizCategoryAddReqDTO;
import com.xujie.manager.DTO.req.BizCategoryQueryReqDTO;
import com.xujie.manager.DTO.res.BizCategoryQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.domain.BO.BizCategoryBO;
import com.xujie.manager.domain.convert.BizCategoryConvert;
import com.xujie.manager.domain.service.BizCategoryDomainService;
import com.xujie.manager.infra.DO.BizCategory;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizCategory)表控制层
 *
 * @author xujie
 * @since 2024-09-27 19:02:09
 */
@RestController
@RequestMapping("/bizCategory")
public class BizCategoryController
    extends BaseController<
        BizCategoryQueryReqDTO,
        BizCategoryQueryResDTO,
        BizCategoryAddReqDTO,
        BizCategoryBO,
        BizCategory,
        BizCategoryConvert,
        BizCategoryDomainService> {

  public BizCategoryController(
      BizCategoryConvert baseConvert, BizCategoryDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }

  /**
   * 获取分类列表
   *
   * @return 分类列表
   */
  @GetMapping("/list")
  public Result<List<BizCategoryQueryResDTO>> list() {
    List<BizCategoryBO> list = baseDomainService.list();
    return Result.ok(baseConvert.convertListBO2DTO(list));
  }
}
