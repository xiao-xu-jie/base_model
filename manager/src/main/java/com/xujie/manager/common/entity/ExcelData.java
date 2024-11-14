package com.xujie.manager.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xujie
 * @since 2024/11/8 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelData {
  private String eggType;
  private String quotationType;
  private String area1;
  private String area2;
  private String userName;
  private Double minPrice;
  private Double avgPrice;
  private Double maxPrice;
  private String phone;
}
