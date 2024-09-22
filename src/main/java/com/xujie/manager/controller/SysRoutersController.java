package com.xujie.manager.controller;


/**
 * (SysRouters)表控制层
 *
 * @author xujie
 * @since 2024-09-22 14:00:51
 */
@RestController
@RequestMapping("/routers")
public class SysRoutersController extends BaseController<RoutersQueryReqDTO, RoutersQueryResDTO, RoutersAddReqDTO, RoutersBO, SysRouters, RoutersConvert, RoutersDomainService> {

    public SysRoutersController(RoutersConvert baseConvert, RoutersDomainService baseDomainService) {
        this.baseConvert = baseConvert;
        this.baseDomainService = baseDomainService;
    }
}

