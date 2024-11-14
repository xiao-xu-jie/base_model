package com.xujie.business.common.templates.twoNinePlatform;

import com.xujie.business.common.templates.PlatformTemplate;

/**
 * @author Xujie
 * @since 2024/11/15 00:05
 */
public class twoNineTemplate extends PlatformTemplate {
  @Override
  protected boolean beforeCheck(Object response) {
    return false;
  }

  @Override
  protected boolean afterSubmitCheck(String response) {
    return false;
  }

  @Override
  protected boolean afterQueryCheck(String response) {
    return false;
  }

  @Override
  protected <T> void handleSubmitFail(T info) {}

  @Override
  protected <T> void handleQueryFail(T info) {}

  @Override
  protected <T> void handleSuccess(T info) {}

  @Override
  protected <T> String submit(T order) {
    return "";
  }

  @Override
  protected <T> String query(T order) {
    return "";
  }
}
