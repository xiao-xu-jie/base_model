package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizCommunityPostAddReqDTO;
import com.xujie.manager.DTO.req.BizCommunityPostQueryReqDTO;
import com.xujie.manager.DTO.res.BizCommunityPostQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizCommunityPostBO;
import com.xujie.manager.domain.convert.BizCommunityPostConvert;
import com.xujie.manager.domain.service.BizCommunityPostDomainService;
import com.xujie.manager.infra.DO.BizCommunityPost;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizCommunityPost)表控制层
 *
 * @author xujie
 * @since 2024-10-31 23:48:36
 */
@RestController
@RequestMapping("/bizCommunityPost")
public class BizCommunityPostController
    extends BaseController<
        BizCommunityPostQueryReqDTO,
        BizCommunityPostQueryResDTO,
        BizCommunityPostAddReqDTO,
        BizCommunityPostBO,
        BizCommunityPost,
        BizCommunityPostConvert,
        BizCommunityPostDomainService> {

  public BizCommunityPostController(
      BizCommunityPostConvert baseConvert, BizCommunityPostDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
