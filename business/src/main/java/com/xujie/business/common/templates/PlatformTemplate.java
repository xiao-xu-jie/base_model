package com.xujie.business.common.templates;

public abstract class PlatformTemplate {
  public <T> void QueryUserClassInfo(T info) {
    if (beforeCheck(info)) {
      handleQueryFail(info);
      return;
    }
    String response = query(info);
    if (afterQueryCheck(response)) {
      handleQueryFail(info);
      return;
    }
  }

  public <T> void SubmitOrder(T info) {
    if (beforeCheck(info)) {
      handleSubmitFail(info);
      return;
    }
    String response = submit(info);
    if (afterSubmitCheck(response)) {
      handleSubmitFail(info);
      return;
    }
    handleSuccess(info);
  }

  protected abstract <T> boolean beforeCheck(T response);

  protected abstract boolean afterSubmitCheck(String response);

  protected abstract boolean afterQueryCheck(String response);

  protected abstract <T> void handleSubmitFail(T info);

  protected abstract <T> void handleQueryFail(T info);

  protected abstract <T> void handleSuccess(T info);

  protected abstract <T> String submit(T order);

  protected abstract <T> String query(T order);
}
