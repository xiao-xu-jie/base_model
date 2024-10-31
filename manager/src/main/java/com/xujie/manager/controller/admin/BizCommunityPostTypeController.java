package com.xujie.manager.controller.admin;

import com.xujie.manager.DTO.req.BizCommunityPostTypeAddReqDTO;
import com.xujie.manager.DTO.req.BizCommunityPostTypeQueryReqDTO;
import com.xujie.manager.DTO.res.BizCommunityPostTypeQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.BizCommunityPostTypeBO;
import com.xujie.manager.domain.convert.BizCommunityPostTypeConvert;
import com.xujie.manager.domain.service.BizCommunityPostTypeDomainService;
import com.xujie.manager.infra.DO.BizCommunityPostType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (BizCommunityPostType)表控制层
 *
 * @author xujie
 * @since 2024-10-31 23:02:23
 */
@RestController
@RequestMapping("/bizCommunityPostType")
public class BizCommunityPostTypeController
    extends BaseController<
        BizCommunityPostTypeQueryReqDTO,
        BizCommunityPostTypeQueryResDTO,
        BizCommunityPostTypeAddReqDTO,
        BizCommunityPostTypeBO,
        BizCommunityPostType,
        BizCommunityPostTypeConvert,
        BizCommunityPostTypeDomainService> {

  public BizCommunityPostTypeController(
      BizCommunityPostTypeConvert baseConvert,
      BizCommunityPostTypeDomainService baseDomainService) {
    this.baseConvert = baseConvert;
    this.baseDomainService = baseDomainService;
  }
}
