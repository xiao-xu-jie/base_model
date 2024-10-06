package com.xujie.business.convert;

import com.xujie.business.DTO.req.cert.UserCertificationSubmitReqDTO;
import com.xujie.business.DTO.res.cert.UserCertificationResDTO;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.infra.DO.BizCertification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificationConvert {
  BizCertificationBO convertDO2BO(BizCertification certificationByEntity);

  BizCertification convertBO2DO(BizCertificationBO certificationBO);

  UserCertificationResDTO convertBO2ResDTO(BizCertificationBO bizCertificationBO);

  BizCertificationBO convertResDTO2BO(UserCertificationSubmitReqDTO userCertificationSubmitReqDTO);
}
