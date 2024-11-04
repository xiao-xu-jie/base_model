package com.xujie.manager.common.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.manager.application.redis.utils.RedisUtils;
import com.xujie.manager.common.utils.DateUtil;
import com.xujie.manager.infra.DO.BizEggQuotation;
import com.xujie.manager.infra.mapper.BizEggQuotationMapper;
import com.xujie.manager.infra.mapper.BizUserMapper;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用户定时任务
 *
 * @author Xujie
 * @since 2024/11/4 14:48
 */
@Component
public class UserTask {

  @Resource private BizUserMapper bizUserMapper;
  @Resource private BizEggQuotationMapper bizEggQuotationMapper;

  /** 定时任务统计用户总数 */
  @Scheduled(cron = "0 0/1 * * * ?")
  public void task() {
    String todayString = DateUtil.getTodayString();
    List list = new ArrayList();
    DateUtil.getWeekDateList()
        .forEach(
            i -> {
              list.add(i);
            });
    RedisUtils.hPut("user:total", todayString, getTotalUser());

    LambdaQueryWrapper<BizEggQuotation> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(BizEggQuotation::getDataDate, todayString);
    RedisUtils.hPut("quotation:total", todayString, bizEggQuotationMapper.selectCount(wrapper));
  }

  private Integer getTotalUser() {
    Long l = bizUserMapper.selectCount(null);
    return l.intValue();
  }
}
