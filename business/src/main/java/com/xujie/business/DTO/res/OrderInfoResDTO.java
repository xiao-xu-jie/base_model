package com.xujie.business.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoResDTO {
  private String urlQrcode;
  private String url;
  private String orderNo;
}
