package com.xujie.manager.controller;

import cn.hutool.json.JSONObject;
import com.xujie.manager.application.redis.utils.RedisUtils;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.common.utils.DateUtil;
import com.xujie.manager.infra.DO.BizCertification;
import com.xujie.manager.infra.service.BizUserCertService;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页数据控制器
 *
 * @author Xujie
 * @since 2024/11/4 15:15
 */
@RequestMapping("/welcome")
@RestController
public class WelcomeController {

  @Resource(name = "asyncExecutor")
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Resource private BizUserCertService bizUserCertService;

  @GetMapping("/index")
  @SuppressWarnings(value = {"unchecked"})
  public Result<ConcurrentMap<String, Object>> index() {
    ConcurrentMap<String, Object> map = new ConcurrentHashMap<>();
    CompletableFuture<Void> userDataFuture =
        CompletableFuture.runAsync(() -> userData(map), threadPoolTaskExecutor);
    CompletableFuture<Void> quotationDataFuture =
        CompletableFuture.runAsync(() -> quotationData(map), threadPoolTaskExecutor);
    CompletableFuture<Void> certDataFuture =
        CompletableFuture.runAsync(() -> certData(map), threadPoolTaskExecutor);
    CompletableFuture<Void> weekDataFuture =
        CompletableFuture.runAsync(() -> weekData(map), threadPoolTaskExecutor);
    CompletableFuture.allOf(userDataFuture, quotationDataFuture, certDataFuture, weekDataFuture)
        .join();
    return Result.ok(map);
  }

  private void weekData(ConcurrentMap<String, Object> map) {
    Optional<Object> cacheObject = RedisUtils.getCacheObject("weekData");
    if (cacheObject.isPresent()) {
      String s = cacheObject.get().toString();
      JSONObject jsonObject = new JSONObject(s);
      map.put("weekData", jsonObject);
    }
  }

  private void certData(ConcurrentMap<String, Object> map) {
    List<BizCertification> listByEntity =
        bizUserCertService.getListByEntity(BizCertification.builder().certStatus(0).build());
    map.put("certTotal", listByEntity.size());
  }

  private void quotationData(ConcurrentMap<String, Object> map) {
    Object o = RedisUtils.hGet("quotation:total", DateUtil.getTodayString());
    map.put("quotationTotal", o == null ? 0 : o);
  }

  private static void userData(ConcurrentMap<String, Object> map) {
    List list = new ArrayList();
    DateUtil.getWeekDateList()
        .forEach(
            item -> {
              list.add(item);
            });
    List<Object> objects = RedisUtils.hLGet("user:total", list);
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) == null) {
        objects.set(i, 0);
      }
    }
    map.put("userTotal", objects);
  }
}
