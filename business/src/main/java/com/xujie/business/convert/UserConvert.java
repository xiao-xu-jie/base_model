package com.xujie.business.convert;

import com.xujie.business.DTO.req.user.UserRegisterReqDTO;
import com.xujie.business.DTO.req.user.UserUpdateReqDTO;
import com.xujie.business.DTO.res.user.UserLoginResDTO;
import com.xujie.business.DTO.res.user.UserSubResDTO;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.infra.DO.BizUser;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {

  UserLoginResDTO convertBO2LoginResDTO(BizUserBO bizUserBO);

  BizUserBO convertDO2BO(BizUser userByEntity);

  BizUserBO convertRegisterReqDTO2BO(UserRegisterReqDTO userRegisterReqDTO);

  BizUser convertBO2DO(BizUserBO userBo);

  BizUserBO convertUpdateReqDTO2BO(UserUpdateReqDTO userUpdateReqDTO);

  List<BizUserBO> convertDOList2BOList(List<BizUser> userListByIds);

  List<UserSubResDTO> convertBOList2SubResDTOList(List<BizUserBO> subscribeList);
}
