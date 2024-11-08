package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizArticleAddReqDTO;
import com.xujie.manager.DTO.req.BizArticleQueryReqDTO;
import com.xujie.manager.DTO.res.BizArticleQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizArticleBO;
import com.xujie.manager.domain.convert.BizArticleConvert;
import com.xujie.manager.domain.service.BizArticleDomainService;
import com.xujie.manager.infra.DO.BizArticle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizArticle)表控制层
 *
 * @author xujie
 * @since 2024-11-08 08:40:33
 */
@RestController
@RequestMapping("/bizArticle")
public class BizArticleController
    extends BaseController<
        BizArticleQueryReqDTO,
        BizArticleQueryResDTO,
        BizArticleAddReqDTO,
        BizArticleBO,
        BizArticle,
        BizArticleConvert,
        BizArticleDomainService> {

  public BizArticleController(
      BizArticleConvert baseConvert, BizArticleDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
