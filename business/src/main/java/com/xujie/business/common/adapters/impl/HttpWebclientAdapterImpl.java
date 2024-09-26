package com.xujie.business.common.adapters.impl;

import cn.hutool.http.body.MultipartBody;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.common.adapters.HttpAdapter;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/**
 * @author Xujie
 * @since 2024/9/26 15:56
 **/

@Component
public class HttpWebclientAdapterImpl implements HttpAdapter{
    @Resource
    private WebClient webClient;
    @Override
    @SuppressWarnings("unchecked")
    public QueryResDTO post(String url, MultiValueMap<String,String> q) {

        QueryResDTO block = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromMultipartData(q))
                .retrieve()
                .bodyToMono(QueryResDTO.class)
                .block();
        return block;
    }
}
