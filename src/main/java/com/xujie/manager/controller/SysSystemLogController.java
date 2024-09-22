package com.xujie.manager.controller;


/**
 * (SysSystemLog)表控制层
 *
 * @author xujie
 * @since 2024-09-22 13:59:55
 */
@RestController
@RequestMapping("/systemLog")
public class SysSystemLogController extends BaseController<SystemLogQueryReqDTO, SystemLogQueryResDTO, SystemLogAddReqDTO, SystemLogBO, SysSystemLog, SystemLogConvert, SystemLogDomainService> {

    public SysSystemLogController(SystemLogConvert baseConvert, SystemLogDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

