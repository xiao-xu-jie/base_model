package com.xujie.business.common.adapters;

import com.xujie.business.DTO.req.WxOrderCreateReqDTO;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.common.entity.Result;
import org.springframework.util.MultiValueMap;

public interface HttpAdapter {
    QueryResDTO post(String url, MultiValueMap<String,String> q);
    Result<String> createOrder(WxOrderCreateReqDTO wxOrderCreateReqDTO);
}
