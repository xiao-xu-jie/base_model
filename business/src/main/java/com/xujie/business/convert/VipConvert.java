package com.xujie.business.convert;

import com.xujie.business.DTO.res.vip.VipQueryResDTO;
import com.xujie.business.domain.BO.BizVipBO;
import com.xujie.business.infra.DO.BizUserVip;
import com.xujie.business.infra.DO.BizVip;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VipConvert {

  List<BizVipBO> convertDOList2BOList(List<BizVip> bizVips);

  List<VipQueryResDTO> convertBOList2ResDTOList(List<BizVipBO> bizVipBOS);

  @Mappings({
    @Mapping(target = "vipName", source = "vipName"),
    @Mapping(target = "vipPostCount", source = "postCount"),
    @Mapping(target = "vipNotifyCount", source = "notifyCount"),
  })
  BizVipBO convertDO2BO(BizUserVip userVipByEntity);
}
