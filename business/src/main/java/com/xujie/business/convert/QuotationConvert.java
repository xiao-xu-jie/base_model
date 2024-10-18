package com.xujie.business.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.business.DTO.req.quotation.BizEggQuotationQueryReqDTO;
import com.xujie.business.DTO.req.quotation.BizUserSubmitTodayEggQuotationReqDTO;
import com.xujie.business.DTO.req.quotation.BizUserUpdateTodayEggQuotationReqDTO;
import com.xujie.business.DTO.res.quotation.*;
import com.xujie.business.common.enums.QuotationTypeEnum;
import com.xujie.business.domain.BO.*;
import com.xujie.business.infra.DO.BizEggQuotation;
import com.xujie.business.infra.DO.BizEggType;
import com.xujie.business.infra.DO.BizUserSubscribe;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface QuotationConvert {
  List<BizEggTypeBO> convertEggTypeDOList2BOList(List<BizEggType> bizEggTypes);

  List<EggTypeResDTO> convertEggTypeBO2ResDTO(List<BizEggTypeBO> bizEggTypeBOS);

  BizEggQuotation convertEggQuotationBO2DO(BizEggQuotationBO entity);

  List<BizEggQuotationBO> convertEggQuotationDOList2BOList(List<BizEggQuotation> bizEggQuotations);

  @Mappings({
    @Mapping(
        target = "quotationType",
        source = "quotationType",
        qualifiedByName = "convertQuotationType"),
  })
  BizEggQuotationResDTO convertEggQuotationBO2ResDTO(BizEggQuotationBO bizEggQuotationBO);

  @Named("convertQuotationType")
  default QuotationTypeEnum convertQuotationType(Integer quotationType) {
    return QuotationTypeEnum.getEnumByCode(quotationType);
  }

  List<BizEggQuotationResDTO> convertEggQuotationBOList2ResDTOList(
      List<BizEggQuotationBO> bizEggQuotationBOS);

  BizEggWeekDataResDTO convertWeekDataBO2DTO(BizWeekDataBO weekData);

  BizAreaUpDownDataResDTO convertAreaUpDownDataBO2DTO(BizAreaUpDownDataBO areaSaleUpDownData);

  Page<BizEggQuotationBO> convertEggQuotationPageDO2BO(Page<BizEggQuotation> bizEggQuotationPage);

  BizEggQuotationBO convertEggQuotationQueryReqDTO2BO(BizEggQuotationQueryReqDTO reqDTO);

  Page<BizEggQuotationQueryResDTO> convertEggQuotationPageBO2ResDTO(
      Page<BizEggQuotationBO> bizEggQuotationBOPage);

  BizEggQuotationBO convertUserSubmitTodayEggQuotationReqDTO2BO(
      BizUserSubmitTodayEggQuotationReqDTO reqDTO);

  List<BizUserSubscribeBO> convertUserSubList2BOList(List<BizUserSubscribe> subscribedUsers);

  List<BizUserQueryQuotationResDTO> convertBOList2UserQueryQuotationResDTOList(
      List<BizEggQuotationBO> boList);

  BizEggQuotationBO convertEggQuotationUpdateDTO2BO(BizUserUpdateTodayEggQuotationReqDTO reqDTO);
}
