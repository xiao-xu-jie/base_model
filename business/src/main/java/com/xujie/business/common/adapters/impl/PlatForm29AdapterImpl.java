package com.xujie.business.common.adapters.impl;

import com.google.common.collect.Maps;
import com.xujie.business.DTO.req.ClassQueryReqDTO;
import com.xujie.business.DTO.res.ClassQueryResDTO;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.common.adapters.HttpAdapter;
import com.xujie.business.common.adapters.PlatFormAdapter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

/**
 * 29平台适配器实现类
 * @author Xujie
 * @since 2024/9/26 15:51
 **/
@Slf4j
@Component
public class PlatForm29AdapterImpl implements PlatFormAdapter<ClassQueryReqDTO, List<ClassQueryResDTO>, ClassQueryReqDTO, ClassQueryResDTO> {

    @Resource(name = "httpWebclientAdapterImpl")
    private HttpAdapter httpAdapter;

    @Override
    public List<ClassQueryResDTO> queryUserClass(ClassQueryReqDTO classQueryReqDTO) {

        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("user", classQueryReqDTO.getUser());
        map.add("pass", classQueryReqDTO.getPass());
        QueryResDTO post = httpAdapter.post("http://2024.shenqixx.cn/api.php?act=get", map);
        log.info("请求结果：{}", post);
        return post.getData();
    }

    @Override
    public ClassQueryResDTO submitOrder(ClassQueryReqDTO classQueryReqDTO) {
        return null;
    }
}
