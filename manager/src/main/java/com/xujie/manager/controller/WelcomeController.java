package com.xujie.manager.controller;

import com.xujie.manager.application.redis.utils.RedisUtils;
import com.xujie.manager.common.entity.Result;
import com.xujie.manager.common.utils.DateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  @GetMapping("/index")
  @SuppressWarnings(value = {"unchecked"})
  public Result<HashMap<String, Object>> index() {
    HashMap<String, Object> map = new HashMap<>();
    userData(map);
    quotationData(map);
    return Result.ok(map);
  }

  private void quotationData(HashMap<String, Object> map) {
    Object o = RedisUtils.hGet("quotation:total", DateUtil.getTodayString());
    map.put("quotationTotal", o == null ? 0 : o);
  }

  private static void userData(HashMap<String, Object> map) {
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
