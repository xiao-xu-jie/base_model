package com.xujie.manager;

import com.alibaba.excel.EasyExcel;
import com.xujie.manager.common.entity.ExcelData;
import com.xujie.manager.common.utils.DemoHeadDataListener;

/**
 * @author Xujie
 * @since 2024/11/8 16:25
 */
public class TestExcel {
  public static void main(String[] args) {
    EasyExcel.read(
            "C:\\Users\\Administrator\\Desktop\\excel.xlsx",
            ExcelData.class,
            new DemoHeadDataListener())
        .sheet()
        .doRead();
  }
}
