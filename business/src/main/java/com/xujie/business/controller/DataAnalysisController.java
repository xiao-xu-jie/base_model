package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.json.JSONUtil;
import com.xujie.business.DTO.res.quotation.BizAreaUpDownDataResDTO;
import com.xujie.business.DTO.res.quotation.BizEggWeekDataResDTO;
import com.xujie.business.application.redis.utils.RedisUtils;
import com.xujie.business.common.entity.Result;
import com.xujie.business.common.utils.DateUtil;
import com.xujie.business.convert.QuotationConvert;
import com.xujie.business.domain.BO.BizAreaUpDownDataBO;
import com.xujie.business.domain.service.DataAnalysisDomainService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据统计控制器
 *
 * @author Xujie
 * @since 2024/10/8 12:53
 */
@RestController
@RequestMapping("/dataAnalysis")
@SaIgnore
public class DataAnalysisController {

  @Resource private DataAnalysisDomainService dataAnalysisDomainService;
  @Resource private QuotationConvert quotationConvert;

  /**
   * 获取周数据
   *
   * @param typeId 类型ID
   * @return 周数据
   */
  @GetMapping("/getWeekData")
  public Result<BizEggWeekDataResDTO> getWeekData(@RequestParam("typeId") Long typeId) {
    BizEggWeekDataResDTO bizEggWeekDataResDTO =
        quotationConvert.convertWeekDataBO2DTO(dataAnalysisDomainService.getWeekData(typeId));
    bizEggWeekDataResDTO.setDateList(DateUtil.getWeekDateList());
    RedisUtils.setCacheObject("weekData", JSONUtil.toJsonStr(bizEggWeekDataResDTO));
    return Result.ok(bizEggWeekDataResDTO);
  }

  /**
   * 获取区域上涨下涨数据
   *
   * @param typeId 类型ID
   * @return 区域上涨下涨数据
   */
  @GetMapping("/getAreaUpDownData")
  public Result<BizAreaUpDownDataResDTO> getAreaUpDownData(@RequestParam("typeId") Long typeId) {
    BizAreaUpDownDataBO areaSaleUpDownData =
        dataAnalysisDomainService.getAreaSaleUpDownData(typeId);
    return Result.ok(quotationConvert.convertAreaUpDownDataBO2DTO(areaSaleUpDownData));
  }
}
