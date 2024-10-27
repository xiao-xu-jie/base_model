package com.xujie.business.common.adapters.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.xujie.business.DTO.req.WxOrderCreateReqDTO;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.common.adapters.HttpAdapter;
import com.xujie.business.common.entity.Result;
import jakarta.annotation.Resource;
import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Xujie
 * @since 2024/9/26 15:56
 */
@Component
public class HttpWebclientAdapterImpl implements HttpAdapter {
  @Resource private WebClient webClient;

  //  private final String REDIS_PREFIX_KEY = "http:webclient:";
  //  private final String LOCK_PREFIX_KEY = "lock:";
  //  @Resource private RedissonClient redissonClient;

  @Override
  @SuppressWarnings("unchecked")
  public QueryResDTO post(String url, MultiValueMap<String, String> q) {
    //    String hashKey = getHashKey(url, q);
    //    Optional<Object> cacheObject = RedisUtils.getCacheObject(REDIS_PREFIX_KEY + hashKey);
    //    if (cacheObject.isPresent()) {
    //      return JSON.parseObject(cacheObject.get().toString(), QueryResDTO.class);
    //    } else {
    //      RLock lock = redissonClient.getLock(LOCK_PREFIX_KEY + hashKey);
    //      lock.lock();
    //      try {
    //        cacheObject = RedisUtils.getCacheObject(REDIS_PREFIX_KEY + hashKey);
    //        if (cacheObject.isPresent()) {
    //          return JSON.parseObject(cacheObject.get().toString(), QueryResDTO.class);
    //        }
    QueryResDTO block =
        webClient
            .post()
            .uri(url)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromMultipartData(q))
            .retrieve()
            .bodyToMono(QueryResDTO.class)
            .block();
    //        if (block != null && block.getData() != null) {
    //          RedisUtils.setCacheObject(
    //              REDIS_PREFIX_KEY + hashKey, JSONUtil.toJsonStr(block), 7, TimeUnit.DAYS);
    //        }
    //        return block;
    //      } catch (Exception e) {
    //        e.printStackTrace();
    //        throw new RuntimeException(e);
    //      } finally {
    //        lock.unlock();
    //      }
    return block;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Result<String> createOrder(WxOrderCreateReqDTO wxOrderCreateReqDTO) {
    return webClient
        .post()
        .uri("http://110.40.42.211:9928/pay/order/create")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(wxOrderCreateReqDTO))
        .retrieve()
        .bodyToMono(Result.class)
        .block(Duration.ofSeconds(10));
  }

  private String getHashKey(String url, MultiValueMap<String, String> q) {
    return DigestUtil.md5Hex(url + q.toString());
  }
}
