package com.xujie.business.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.DTO.req.community.CommunityPostAddReqDTO;
import com.xujie.business.DTO.req.community.CommunityPostQueryReqDTO;
import com.xujie.business.DTO.res.community.CommunityPostQueryResDTO;
import com.xujie.business.DTO.res.community.CommunityPostTypeResDTO;
import com.xujie.business.DTO.res.community.CommunityPostUpdateReqDTO;
import com.xujie.business.DTO.res.community.CommunityPostUserResDTO;
import com.xujie.business.domain.BO.BizCommunityPostBO;
import com.xujie.business.domain.BO.BizCommunityPostTypeBO;
import com.xujie.business.infra.DO.BizCommunityPost;
import com.xujie.business.infra.DO.BizCommunityPostType;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * 社区帖子
 *
 * @author Xujie
 * @since 2024/10/14 12:47
 */
@Mapper(componentModel = "spring")
public interface CommunityPostConvert {
  BizCommunityPost convertBO2DO(BizCommunityPostBO bizCommunityPostBO);

  Page<BizCommunityPostBO> convertDOPage2BOPage(Page<BizCommunityPost> bizCommunityPostPage);

  Page<CommunityPostQueryResDTO> convertBOPage2QueryResPageDTO(
      Page<BizCommunityPostBO> bizCommunityPostBOPage);

  BizCommunityPostBO convertQueryReqDTO2BO(CommunityPostQueryReqDTO communityPostQueryReqDTO);

  List<BizCommunityPostTypeBO> convertPostTypeDOList2BOList(
      List<BizCommunityPostType> bizCommunityPostTypes);

  List<CommunityPostTypeResDTO> convertPostTypeBOList2ResDTOList(
      List<BizCommunityPostTypeBO> bizCommunityPostTypeBOS);

  BizCommunityPostBO convertDO2BO(BizCommunityPost byEntity);

  CommunityPostQueryResDTO convertBO2ResDTO(BizCommunityPostBO byId);

  BizCommunityPostBO convertQueryAddReqDTO2BO(CommunityPostAddReqDTO communityPostAddReqDTO);

  List<CommunityPostQueryResDTO> convertBOList2ResDTOList(List<BizCommunityPostBO> myPostByType);

  List<BizCommunityPostBO> convertDOList2BOList(List<BizCommunityPost> doList);

  CommunityPostUserResDTO convertBO2UserResDTO(BizCommunityPostBO bo);

  BizCommunityPostBO convertQueryUpdateReqDTO2BO(
      CommunityPostUpdateReqDTO communityPostUpdateReqDTO);
}
