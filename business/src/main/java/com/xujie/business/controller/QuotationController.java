package com.xujie.business.controller;

import com.xujie.business.DTO.req.quotation.BizUserSubmitTodayEggQuotationReqDTO;
import com.xujie.business.DTO.req.quotation.BizUserUpdateTodayEggQuotationReqDTO;
import com.xujie.business.DTO.res.quotation.BizEggQuotationResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.QuotationConvert;
import com.xujie.business.domain.service.QuotationDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报价控制器
 *
 * @author Xujie
 * @since 2024/10/6 11:51
 */
@RestController
@RequestMapping("/quotation")
public class QuotationController {
  @Resource private QuotationDomainService quotationDomainService;
  @Resource private QuotationConvert quotationConvert;

  /**
   * 获取用户今日报价
   *
   * @return 用户今日报价
   */
  @PostMapping("/listUserTodayQuotation")
  public Result<List<BizEggQuotationResDTO>> listUserTodayQuotation() {
    return Result.ok(
        quotationConvert.convertEggQuotationBOList2ResDTOList(
            quotationDomainService.listUserTodayQuotation()));
  }

  /**
   * 用户提交今日报价
   *
   * @param reqDTO 今日报价请求
   * @return 是否提交成功
   */
  @PostMapping("/submit")
  public Result<Boolean> submitTodayQuotation(
      @RequestBody @Validated BizUserSubmitTodayEggQuotationReqDTO reqDTO) {
    return Result.ok(Boolean.TRUE);
  }

  /**
   * 用户更新今日报价
   *
   * @param reqDTO 今日报价请求
   * @return 是否提交成功
   */
  @PostMapping("/update")
  public Result<Boolean> updateTodayQuotation(
      @RequestBody @Validated BizUserUpdateTodayEggQuotationReqDTO reqDTO) {
    return Result.ok(Boolean.TRUE);
  }
}
