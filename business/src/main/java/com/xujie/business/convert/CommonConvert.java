package com.xujie.business.convert;

import com.xujie.business.DTO.res.common.SliderResDTO;
import com.xujie.business.infra.DO.BizSlider;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonConvert {
  List<SliderResDTO> convertBizSliderList2SliderResDTOList(List<BizSlider> bizSliders);
}
