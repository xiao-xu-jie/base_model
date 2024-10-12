package com.xujie.business.convert;

import cn.hutool.core.util.DesensitizedUtil;
import com.xujie.business.DTO.req.cert.UserCertificationSubmitReqDTO;
import com.xujie.business.DTO.res.cert.UserCertificationResDTO;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.infra.DO.BizCertification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CertificationConvert {
  @Mappings({
    @Mapping(target = "idCardNo", source = "idCardNo", qualifiedByName = "idCardNoMask"),
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "phoneNumberMask")
  })
  BizCertificationBO convertDO2BO(BizCertification certificationByEntity);

  BizCertification convertBO2DO(BizCertificationBO certificationBO);

  @Mappings({
    @Mapping(target = "idCardNo", source = "idCardNo", qualifiedByName = "idCardNoMask"),
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "phoneNumberMask")
  })
  UserCertificationResDTO convertBO2ResDTO(BizCertificationBO bizCertificationBO);

  @Named("idCardNoMask")
  default String idCardNoMask(String idCardNo) {
    return DesensitizedUtil.idCardNum(idCardNo, 3, 4);
  }

  @Named("phoneNumberMask")
  default String phoneNumberMask(String phoneNumber) {
    return DesensitizedUtil.mobilePhone(phoneNumber);
  }

  BizCertificationBO convertResDTO2BO(UserCertificationSubmitReqDTO userCertificationSubmitReqDTO);
}
