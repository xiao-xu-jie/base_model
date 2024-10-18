package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.DTO.req.community.CommunityPostAddReqDTO;
import com.xujie.business.DTO.req.community.CommunityPostQueryReqDTO;
import com.xujie.business.DTO.res.community.CommunityPostQueryResDTO;
import com.xujie.business.DTO.res.community.CommunityPostTypeResDTO;
import com.xujie.business.DTO.res.community.CommunityPostUpdateReqDTO;
import com.xujie.business.DTO.res.community.CommunityPostUserResDTO;
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

  /**
   * 根据id获取社区帖子
   *
   * @param id 帖子id
   * @return 社区帖子
   */
  @GetMapping("/getById")
  public Result<CommunityPostQueryResDTO> getById(@RequestParam("id") Long id) {
    BizCommunityPostBO byId = communityDomainService.getById(id);
    return Result.ok(communityPostConvert.convertBO2ResDTO(byId));
  }

  /**
   * 保存社区帖子
   *
   * @param communityPostAddReqDTO 社区帖子
   * @return 社区帖子
   */
  @PostMapping("/save")
  public Result<Boolean> save(
      @RequestBody @Validated CommunityPostAddReqDTO communityPostAddReqDTO) {
    communityDomainService.save(
        communityPostConvert.convertQueryAddReqDTO2BO(communityPostAddReqDTO));
    return Result.ok(Boolean.TRUE);
  }

  /**
   * 根据类型获取我的帖子
   *
   * @param typeId 类型id
   * @return 我的帖子
   */
  @GetMapping("/getMyPostByType")
  public Result<List<CommunityPostQueryResDTO>> getMyPostByType(
      @RequestParam(value = "type", required = true) Long typeId) {
    List<BizCommunityPostBO> myPostByType = communityDomainService.getMyPostByType(typeId);
    return Result.ok(communityPostConvert.convertBOList2ResDTOList(myPostByType));
  }

  /**
   * 根据id获取用户帖子
   *
   * @param id 帖子id
   * @return 用户帖子
   */
  @GetMapping("/getUserPostById")
  public Result<CommunityPostUserResDTO> getUserPostById(@RequestParam("id") Long id) {
    BizCommunityPostBO bo = communityDomainService.getUserPostById(id);
    return Result.ok(communityPostConvert.convertBO2UserResDTO(bo));
  }

  /**
   * 更新社区帖子
   *
   * @param communityPostUpdateReqDTO
   * @return
   */
  @PostMapping("/update")
  public Result<Boolean> update(
      @RequestBody @Validated CommunityPostUpdateReqDTO communityPostUpdateReqDTO) {
    communityDomainService.update(
        communityPostConvert.convertQueryUpdateReqDTO2BO(communityPostUpdateReqDTO));
    return Result.ok(Boolean.TRUE);
  }
}
