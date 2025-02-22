package com.xujie.business.domain.service.impl;

import cn.hutool.json.JSONUtil;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.common.constants.PlantApiConstant;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.templates.PlatformTemplate;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryOrderStatusRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryUserClassRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.SubmitOrderRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.response.SubmitOrderResponse;
import com.xujie.business.domain.BO.QueryClassBO;
import com.xujie.business.domain.service.ClassDomainService;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.DO.BizSourceStation;
import com.xujie.business.infra.service.CategoryGoodService;
import com.xujie.business.infra.service.SourceStationService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

/**
 * @author Xujie
 * @since 2024/11/26 20:47
 */
@Slf4j
@Service
public class ClassDomainServiceImpl implements ClassDomainService {
    @Resource
    private SourceStationService sourceStationService;
    @Resource
    private CategoryGoodService categoryGoodService;

    @Resource
    private PlatformTemplate<SubmitOrderRequest, SubmitOrderResponse, QueryUserClassRequest, QueryOrderStatusRequest>
            platformTemplate;

    @Override
    public QueryResDTO queryClassInfo(QueryClassBO queryBO) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("school", "school");
        map.add("user", queryBO.getUser());
        map.add("pass", queryBO.getPass());
        // 查询商品对应的货源站
        BizSourceStation bizSourceStation = checkAndGetInfo(queryBO, map);
        QueryUserClassRequest request =
                QueryUserClassRequest.builder()
                        .data(map)
                        .url(bizSourceStation.getStationUrl() + PlantApiConstant.QUERY_CLASS_SUFFIX)
                        .build();
        String response = platformTemplate.queryUserClassInfo(request);
        return JSONUtil.toBean(response, QueryResDTO.class);
    }

    private BizSourceStation checkAndGetInfo(
            QueryClassBO queryBO, MultiValueMap<String, String> map) {
        List<BizGood> goodListByEntity =
                categoryGoodService.getGoodListByEntity(BizGood.builder().id(queryBO.getGood_id()).build());
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
        return bizSourceStation;
    }
}
