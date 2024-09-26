package com.xujie.business.common.adapters;

import com.xujie.business.DTO.res.QueryResDTO;
import org.springframework.util.MultiValueMap;

public interface HttpAdapter {
    QueryResDTO post(String url, MultiValueMap<String,String> q);
}
