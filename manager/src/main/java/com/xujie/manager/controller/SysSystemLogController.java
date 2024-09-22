package com.xujie.manager.controller;


import com.xujie.manager.DTO.req.SystemLogAddReqDTO;
import com.xujie.manager.DTO.req.SystemLogQueryReqDTO;
import com.xujie.manager.DTO.res.SystemLogQueryResDTO;
import com.xujie.manager.common.base.controller.BaseController;
import com.xujie.manager.domain.BO.SystemLogBO;
import com.xujie.manager.domain.convert.SystemLogConvert;
import com.xujie.manager.domain.service.SystemLogDomainService;
import com.xujie.manager.infra.DO.SysSystemLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (SysSystemLog)表控制层
 *
 * @author xujie
 * @since 2024-09-22 10:18:05
 */
@RestController
@RequestMapping("/systemLog")
public class SysSystemLogController extends BaseController<SystemLogQueryReqDTO, SystemLogQueryResDTO, SystemLogAddReqDTO, SystemLogBO, SysSystemLog, SystemLogConvert, SystemLogDomainService> {

    public SysSystemLogController(SystemLogConvert baseConvert, SystemLogDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

