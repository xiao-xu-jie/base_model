package com.xujie.business.common.utils;

import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Xujie
 * @since 2024/11/15 13:17
 */
@Component
public class WebclientUtil {

  private static WebClient webClient;

  public WebclientUtil(WebClient webClient) {
    WebclientUtil.webClient = webClient;
  }

  public static <T> T post(
      String url,
      MultiValueMap<String, String> request,
      MediaType contentType,
      Class<T> responseClass,
      int timeout) {
    return webClient
        .post()
        .uri(url)
        .contentType(contentType)
        .body(BodyInserters.fromMultipartData(request))
        .retrieve()
        .bodyToMono(responseClass)
        .block(Duration.ofSeconds(timeout));
  }
}
