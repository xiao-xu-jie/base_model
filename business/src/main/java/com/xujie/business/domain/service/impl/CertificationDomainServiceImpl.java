package com.xujie.business.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.google.gson.JsonObject;
import com.xujie.business.common.enums.CertificationStatusEnum;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.convert.CertificationConvert;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.domain.service.CertificationDomainService;
import com.xujie.business.infra.DO.BizCertification;
import com.xujie.business.infra.service.CertificationService;
import com.xujie.tools.ConditionCheck;
import com.xujie.wx.utils.WxAppUtil;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 认证领域服务实现
 *
 * @author Xujie
 * @since 2024/10/6 11:23
 */
@Slf4j
@Service
public class CertificationDomainServiceImpl implements CertificationDomainService {
  @Resource private CertificationService certificationService;
  @Resource private CertificationConvert certificationConvert;
  @Resource private WxAppUtil wxAppUtil;
  @Resource private WebClient webClient;
  @Resource private FileStorageService fileStorageService;

  /**
   * 通过实体获取认证信息
   *
   * @param userId 用户id
   * @return 认证信息
   */
  @Override
  public BizCertificationBO getMyCertification(Long userId) {
    BizCertification certificationByEntity =
        certificationService.getCertificationByEntity(
            BizCertification.builder().userId(userId).build());
    return certificationConvert.convertDO2BO(certificationByEntity);
  }

  /**
   * 插入认证信息
   *
   * @param certificationBO 认证信息
   */
  @Override
  public void submit(BizCertificationBO certificationBO) {
    BizCertification certificationByEntity =
        certificationService.getCertificationByEntity(
            BizCertification.builder().userId(certificationBO.getUserId()).build());

    ConditionCheck.trueAndThrow(
        certificationByEntity != null
            && CertificationStatusEnum.APPROVED.equals(certificationByEntity.getCertStatus()),
        new CustomException("已经提交过认证信息了"));
    if (certificationByEntity != null
        && CertificationStatusEnum.DID_NOT_PASS.equals(certificationByEntity.getCertStatus())) {
      certificationBO.setCertStatus(CertificationStatusEnum.UNDER_REVIEW);
      certificationBO.setId(certificationByEntity.getId());
    }
    certificationBO.setUserId(StpUtil.getLoginIdAsLong());
    certificationService.insertCertification(certificationConvert.convertBO2DO(certificationBO));
  }

  @Override
  public Map<String, String> uploadUserCard(MultipartFile file) {
    FileInfo upload = fileStorageService.of(file).upload();
    return getUserCardInfo(upload.getUrl());
  }

  public Map<String, String> getUserCardInfo(String imgUrl) {
    String accessToken = wxAppUtil.getToken().getAccessToken();
    String url = "https://api.weixin.qq.com/cv/ocr/idcard?img_url=%s&access_token=%s";
    url = String.format(url, imgUrl, accessToken);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("img_url", imgUrl);
    jsonObject.addProperty("access_token", accessToken);
    String post = HttpUtil.post(url, "");
    log.info("[用户认证][上传身份证]: {}", post);
    JSONObject res = new JSONObject(post);
    Map<String, String> map = new HashMap<>();
    map.put("name", res.getStr("name", ""));
    map.put("id", res.getStr("id", ""));
    if (!res.getStr("errmsg").equals("ok")) {
      throw new CustomException("身份证识别失败");
    }
    map.put("url", imgUrl);
    return map;
  }
}
