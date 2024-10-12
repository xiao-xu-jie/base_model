package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.business.DTO.res.common.SliderResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.common.enums.ShowStatusEnum;
import com.xujie.business.convert.CommonConvert;
import com.xujie.business.infra.DO.BizSlider;
import com.xujie.business.infra.mapper.BizSliderMapper;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用控制器
 *
 * @author Xujie
 * @since 2024/10/11 20:24
 */
@RestController
@RequestMapping("/common")
@SaIgnore
public class CommonController {
  /** 轮播图Mapper */
  @Resource private BizSliderMapper bizSliderMapper;

  @Resource private CommonConvert commonConvert;

  /**
   * 获取发布页面轮播图
   *
   * @return 发布页面轮播图
   */
  @GetMapping("/listSlider")
  public Result<List<SliderResDTO>> listSlider() {
    return Result.ok(
        commonConvert.convertBizSliderList2SliderResDTOList(
            bizSliderMapper.selectList(
                Wrappers.<BizSlider>lambdaQuery()
                    .orderByAsc(BizSlider::getRank)
                    .eq(BizSlider::getShowStatus, ShowStatusEnum.SHOW))));
  }
}
