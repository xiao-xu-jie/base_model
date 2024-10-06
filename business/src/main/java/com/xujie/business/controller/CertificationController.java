package com.xujie.business.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.xujie.business.DTO.req.cert.UserCertificationSubmitReqDTO;
import com.xujie.business.DTO.res.cert.UserCertificationResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.convert.CertificationConvert;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.domain.service.CertificationDomainService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 *
 * @author Xujie
 * @since 2024/10/6 11:27
 */
@RestController
@RequestMapping("/certification")
public class CertificationController {
  @Resource private CertificationDomainService certificationDomainService;
  @Resource private CertificationConvert certificationConvert;

  /**
   * 获取我的认证信息
   *
   * @return 认证信息
   */
  @GetMapping("/getMyCertification")
  public Result<UserCertificationResDTO> getMyCertification() {
    BizCertificationBO bizCertificationBO =
        certificationDomainService.getMyCertification(StpUtil.getLoginIdAsLong());
    return Result.ok(certificationConvert.convertBO2ResDTO(bizCertificationBO));
  }

  /**
   * 提交认证信息
   *
   * @param userCertificationSubmitReqDTO 认证信息
   * @return 是否提交成功
   */
  @PostMapping("/submit")
  public Result<Boolean> submit(
      @RequestBody UserCertificationSubmitReqDTO userCertificationSubmitReqDTO) {
    certificationDomainService.submit(
        certificationConvert.convertResDTO2BO(userCertificationSubmitReqDTO));
    return Result.ok(Boolean.TRUE);
  }
}
