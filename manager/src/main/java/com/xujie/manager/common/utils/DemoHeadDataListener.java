package com.xujie.manager.common.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xujie.manager.common.entity.ExcelData;

/**
 * @author Xujie
 * @since 2024/11/8 17:12
 */
public class DemoHeadDataListener extends AnalysisEventListener<ExcelData> {
  @Override
  public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
    try {
      System.out.println("解析到一条数据:" + excelData);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

  @Override
  public void onException(Exception exception, AnalysisContext context) throws Exception {
    super.onException(exception, context);
  }
}
