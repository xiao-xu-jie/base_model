package com.xujie.business.common.templates.twoNinePlatform.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Xujie
 * @since 2025/2/22 14:33
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrderStatusRequest {
    String url;
    String phone;
    String password;
    Integer platform;
    String platformUid;

    public MultiValueMap<String, String> getMapVal() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", phone);
        map.add("pass", password);
        map.add("noun", String.valueOf(platform));
        map.add("uid", platformUid);
        return map;
    }
}
