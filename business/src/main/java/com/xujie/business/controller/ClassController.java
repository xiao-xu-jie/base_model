package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.DTO.req.ClassQueryReqDTO;
import com.xujie.business.DTO.res.ClassQueryResDTO;
import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.common.adapters.impl.PlatForm29AdapterImpl;
import com.xujie.business.common.entity.Result;
import com.xujie.business.domain.convert.ClassConvert;
import com.xujie.business.domain.service.ClassDomainService;
import jakarta.annotation.Resource;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程控制器
 *
 * @author Xujie
 * @since 2024/9/27 14:08
 */
@SaIgnore
@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    private ClassConvert classConvert;
    @Resource
    private PlatForm29AdapterImpl platForm29Adapter;

    @Resource
    private ClassDomainService classDomainService;

    /**
     * 查课
     *
     * @return 用户课程信息
     */
    @Trace(operationName = "查课")
    @Tags({@Tag(key = "param", value = "arg[0]"),
            @Tag(key = "classes", value = "returnedObj")})
    @PostMapping("/query")
    public Result<List<ClassQueryResDTO>> query(
            @RequestBody @Validated ClassQueryReqDTO classQueryReq) {
        QueryResDTO queryResDTO =
                classDomainService.queryClassInfo(classConvert.convertToBO(classQueryReq));
        return Result.ok(queryResDTO.getData());
    }
}
