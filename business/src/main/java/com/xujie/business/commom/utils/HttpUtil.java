package com.xujie.business.commom.utils;

import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

/**
 * HTTP 请求工具类，基于 WebClient 实现
 */
@Component
public class HttpUtil {

    @Resource
    private WebClient webClient;

    /**
     * 发送 GET 请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  URL 参数
     * @return 响应实体
     */
    public Mono<ResponseEntity<String>> get(String url, Map<String, String> headers, MultiValueMap<String, String> params) {
        return webClient.get()
                .uri(t -> {
                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
                    builder.queryParams(params);
                    URI build = builder.build().toUri();
                    return build;
                })
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .retrieve()
                .toEntity(String.class);
    }

    /**
     * 发送 POST 请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param body    请求体
     * @return 响应实体
     */
    public Mono<ResponseEntity<String>> post(String url, Map<String, String> headers, Object body) {
        return webClient.post()
                .uri(url)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class);
    }
}