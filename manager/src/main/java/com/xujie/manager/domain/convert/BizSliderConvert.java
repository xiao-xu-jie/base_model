package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.req.BizSliderAddReqDTO;
import com.xujie.manager.DTO.req.BizSliderQueryReqDTO;
import com.xujie.manager.DTO.res.BizSliderQueryResDTO;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.domain.BO.BizSliderBO;
import com.xujie.manager.infra.DO.BizSlider;
import org.mapstruct.Mapper;

/**
 * 发布页面轮播(BizSlider)Convert 类
 *
 * @author xujie
 * @since 2024-11-07 09:07:49
 */
@Mapper(componentModel = "spring")
public interface BizSliderConvert
    extends BaseConvert<
        BizSliderQueryResDTO, BizSliderAddReqDTO, BizSliderQueryReqDTO, BizSliderBO, BizSlider> {}
