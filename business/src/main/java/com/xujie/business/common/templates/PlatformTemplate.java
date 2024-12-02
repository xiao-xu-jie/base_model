package com.xujie.business.common.templates;

import cn.hutool.json.JSONUtil;
import com.xujie.business.common.annotations.MyCache;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PlatformTemplate<Q, QR, CQ> {
  @MyCache(key = "query:class", expire = 7, timeUnit = TimeUnit.DAYS)
  public <T> String queryUserClassInfo(CQ info) {
    String response = query(info);
    if (log.isInfoEnabled()) {

      log.debug("[TwoNineTemplate]查询课程返回信息：{}", JSONUtil.parseObj(response).toJSONString(4));
    }
    if (!afterQueryCheck(response)) {
      handleQueryFail(response);
    }
    return response;
  }

  public void SubmitOrder(Q info) {
    if (!beforeCheck(info)) {
      handleSubmitFail(info);
      return;
    }
    QR response = submit(info);
    if (!afterSubmitCheck(response)) {
      handleSubmitFail(info);
      return;
    }
    handleSuccess(info);
  }

  protected abstract boolean beforeCheck(Q response);

  protected abstract boolean afterSubmitCheck(QR response);

  protected abstract boolean afterQueryCheck(String response);

  protected abstract void handleSubmitFail(Q info);

  protected abstract void handleQueryFail(String info);

  protected abstract void handleSuccess(Q info);

  protected abstract QR submit(Q order);

  protected abstract <T> String query(CQ order);
}
