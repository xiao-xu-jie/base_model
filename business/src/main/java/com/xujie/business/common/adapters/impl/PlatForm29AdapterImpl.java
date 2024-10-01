package com.xujie.business.common.adapters.impl;

import com.xujie.business.DTO.req.ClassQueryReqDTO;
import com.xujie.business.DTO.res.ClassQueryResDTO;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.DTO.res.SubmitOrderResDTO;
import com.xujie.business.common.adapters.HttpAdapter;
import com.xujie.business.common.adapters.PlatFormAdapter;
import com.xujie.business.common.constants.PlantApiConstant;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.DO.BizSourceStation;
import com.xujie.business.infra.service.CategoryGoodService;
import com.xujie.business.infra.service.SourceStationService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 29平台适配器实现类
 *
 * @author Xujie
 * @since 2024/9/26 15:51
 */
@Slf4j
@Component
public class PlatForm29AdapterImpl
    implements PlatFormAdapter<
        ClassQueryReqDTO,
        List<ClassQueryResDTO>,
        MultiValueMap<String, String>,
        SubmitOrderResDTO> {

  @Resource(name = "httpWebclientAdapterImpl")
  private HttpAdapter httpAdapter;

  @Resource private SourceStationService sourceStationService;
  @Resource private CategoryGoodService categoryGoodService;
  @Resource private WebClient webClient;

  @Cacheable(
      value = "class",
      key = "#p0.user+':'+#p0.pass+':'+#p0.good_id",
      unless = "#result == null")
  @Override
  public List<ClassQueryResDTO> queryUserClass(ClassQueryReqDTO classQueryReqDTO) {

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("school", "school");
    map.add("user", classQueryReqDTO.getUser());
    map.add("pass", classQueryReqDTO.getPass());
    // 查询商品对应的货源站
    List<BizGood> goodListByEntity =
        categoryGoodService.getGoodListByEntity(
            BizGood.builder().id(classQueryReqDTO.getGood_id()).build());
    BizGood bizGood =
        goodListByEntity.stream().findFirst().orElseThrow(() -> new CustomException("商品不存在"));
    ConditionCheck.nullAndThrow(bizGood.getPlatformId(), new CustomException("商品信息不完整"));
    map.add("platform", String.valueOf(bizGood.getPlatformId()));
    Long stationId =
        Optional.of(bizGood)
            .map(BizGood::getStationId)
            .orElseThrow(() -> new CustomException("商品信息不完整"));
    List<BizSourceStation> sourceStationListByEntity =
        sourceStationService.getSourceStationListByEntity(
            BizSourceStation.builder().id(stationId).build());
    BizSourceStation bizSourceStation =
        sourceStationListByEntity.stream()
            .findFirst()
            .orElseThrow(() -> new CustomException("货源站不存在"));
    String url =
        Optional.ofNullable(bizSourceStation)
                .map(BizSourceStation::getStationUrl)
                .orElseThrow(() -> new CustomException("货源站点信息错误"))
            + PlantApiConstant.QUERY_CLASS_SUFFIX;
    map.add(
        "uid",
        Optional.of(bizSourceStation)
            .map(BizSourceStation::getUid)
            .orElseThrow(() -> new CustomException("货源站点信息错误")));
    map.add(
        "key",
        Optional.of(bizSourceStation)
            .map(BizSourceStation::getSecret)
            .orElseThrow(() -> new CustomException("货源站点信息错误")));
    QueryResDTO post = new QueryResDTO();
    try {
      post = httpAdapter.post(url, map);
    } catch (Exception e) {
      throw new CustomException("请求失败");
    } finally {
      log.info("查课请求结果：{}", post);
    }
    Optional.ofNullable(post)
        .map(QueryResDTO::getMsg)
        .ifPresent(
            msg -> {
              if (!msg.contains("成功")) {
                throw new CustomException(msg);
              }
            });
    return Optional.ofNullable(post).map(QueryResDTO::getData).orElse(null);
  }

  @Override
  public SubmitOrderResDTO submitOrder(MultiValueMap<String, String> map) {
    String url = map.get("url").get(0) + PlantApiConstant.SUBMIT_CLASS_SUFFIX;
    SubmitOrderResDTO post = new SubmitOrderResDTO();
    try {
      post =
          webClient
              .post()
              .uri(url)
              .contentType(MediaType.APPLICATION_FORM_URLENCODED)
              .body(BodyInserters.fromMultipartData(map))
              .retrieve()
              .bodyToMono(SubmitOrderResDTO.class)
              .block(Duration.of(10, ChronoUnit.SECONDS));

      if (post.getCode() != null && post.getCode() != 0) {
        throw new CustomException(post.getMsg());
      }
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    } finally {
      log.info("订单提交请求结果：{}", post);
    }
    return post;
  }
}
