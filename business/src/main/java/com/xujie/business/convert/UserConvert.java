package com.xujie.business.convert;

import com.xujie.business.DTO.res.user.UserLoginResDTO;
import com.xujie.business.domain.BO.BizUserBO;
import com.xujie.business.infra.DO.BizUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvert {
  UserLoginResDTO convertBO2LoginResDTO(BizUserBO bizUserBO);

  BizUserBO convertDO2BO(BizUser userByEntity);
}
