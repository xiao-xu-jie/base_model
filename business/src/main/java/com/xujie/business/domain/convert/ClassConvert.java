package com.xujie.business.domain.convert;

import com.xujie.business.DTO.req.ClassQueryReqDTO;
import com.xujie.business.domain.BO.QueryClassBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassConvert {
  QueryClassBO convertToBO(ClassQueryReqDTO dto);
}
