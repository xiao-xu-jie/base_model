package com.xujie.business.DTO.res.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 轮播图响应DTO
 *
 * @author Xujie
 * @since 2024/10/11 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderResDTO {
  private String imgUrl;
  private String content;
}
