package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.DTO.req.community.CommunityPostQueryReqDTO;
import com.xujie.business.DTO.res.community.CommunityPostQueryResDTO;
import com.xujie.business.DTO.res.community.CommunityPostTypeResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.CommunityPostConvert;
import com.xujie.business.domain.BO.BizCommunityPostBO;
import com.xujie.business.domain.BO.BizCommunityPostTypeBO;
import com.xujie.business.domain.service.CommunityDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 社区帖子控制器
 *
 * @author Xujie
 * @since 2024/10/14 12:50
 */
@RestController
@RequestMapping("/community")
public class CommunityPostController {
  @Resource private CommunityDomainService communityDomainService;
  @Resource private CommunityPostConvert communityPostConvert;

  /**
   * 展示社区帖子
   *
   * @return 社区帖子
   */
  @SaIgnore
  @PostMapping("/selectPage")
  public Result<Page<CommunityPostQueryResDTO>> selectPage(
      @RequestBody @Validated CommunityPostQueryReqDTO communityPostQueryReqDTO) {
    Page<BizCommunityPostBO> bizCommunityPostBOPage =
        communityDomainService.selectPage(
            communityPostConvert.convertQueryReqDTO2BO(communityPostQueryReqDTO),
            communityPostQueryReqDTO.getPageNum(),
            communityPostQueryReqDTO.getPageSize());
    return Result.ok(communityPostConvert.convertBOPage2QueryResPageDTO(bizCommunityPostBOPage));
  }

  /**
   * 展示社区帖子类型
   *
   * @return 社区帖子类型
   */
  @SaIgnore
  @GetMapping("/selectPostTypeList")
  public Result<List<CommunityPostTypeResDTO>> selectPostTypeList() {
    List<BizCommunityPostTypeBO> bizCommunityPostTypeBOS =
        communityDomainService.selectPostTypeList();
    return Result.ok(
        communityPostConvert.convertPostTypeBOList2ResDTOList(bizCommunityPostTypeBOS));
  }
}
