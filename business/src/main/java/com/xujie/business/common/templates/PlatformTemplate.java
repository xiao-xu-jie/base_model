package com.xujie.business.common.templates;

public abstract class PlatformTemplate<Q, QR> {
  public <T> void QueryUserClassInfo(T info) {
    String response = query(info);
    if (!afterQueryCheck(response)) {
      handleQueryFail(info);
      return;
    }
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

  protected abstract <T> void handleQueryFail(T info);

  protected abstract void handleSuccess(Q info);

  protected abstract QR submit(Q order);

  protected abstract <T> String query(T order);
}
