package com.xujie.manager.controller;


import com.xujie.manager.DTO.req.OperLogAddReqDTO;
import com.xujie.manager.DTO.req.OperLogQueryReqDTO;
import com.xujie.manager.DTO.res.OperLogQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.OperLogBO;
import com.xujie.manager.domain.convert.OperLogConvert;
import com.xujie.manager.domain.service.OperLogDomainService;
import com.xujie.manager.infra.DO.SysOperLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (OperLog)表控制层
 *
 * @author xujie
 * @since 2024-09-25 16:00:16
 */
@RestController
@RequestMapping("/operLog")
public class OperLogController extends BaseController<OperLogQueryReqDTO, OperLogQueryResDTO, OperLogAddReqDTO, OperLogBO, SysOperLog, OperLogConvert, OperLogDomainService> {

    public OperLogController(OperLogConvert baseConvert, OperLogDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

