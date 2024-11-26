package com.xujie.business.common.templates.twoNinePlatform.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

/**
 * @author Xujie
 * @since 2024/11/26 20:52
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QueryUserClassRequest {
  String url;
  MultiValueMap<String, String> data;
}
