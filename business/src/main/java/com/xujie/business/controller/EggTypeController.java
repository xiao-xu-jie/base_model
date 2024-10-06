package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.DTO.res.quotation.EggTypeResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.QuotationConvert;
import com.xujie.business.domain.BO.BizEggTypeBO;
import com.xujie.business.domain.service.QuotationDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 蛋种类控制器
 *
 * @author Xujie
 * @since 2024/10/6 12:27
 */
@RestController
@RequestMapping("/eggType")
public class EggTypeController {
  @Resource private QuotationDomainService quotationDomainService;
  @Resource private QuotationConvert quotationConvert;

  /**
   * 获取蛋种类列表
   *
   * @return 蛋种类列表
   */
  @SaIgnore
  @GetMapping("/list")
  public Result<List<EggTypeResDTO>> listEggType() {
    List<BizEggTypeBO> bizEggTypeBOS = quotationDomainService.listEggType();
    return Result.ok(quotationConvert.convertEggTypeBO2ResDTO(bizEggTypeBOS));
  }
}
