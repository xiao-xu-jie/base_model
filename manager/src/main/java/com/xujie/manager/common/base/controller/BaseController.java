package com.xujie.manager.common.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.common.base.convert.BaseConvert;
import com.xujie.manager.common.base.model.BaseBO;
import com.xujie.manager.common.base.model.BaseDO;
import com.xujie.manager.common.base.model.BaseDTO;
import com.xujie.manager.common.base.service.BaseDomainService;
import com.xujie.manager.common.entity.Groups;
import com.xujie.manager.common.entity.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 基础控制器
 *
 * @author Xujie
 * @since 2024/9/21 13:02
 **/


public abstract class BaseController<QueryReq extends BaseDTO
        , QueryRes extends BaseDTO
        , AddReq extends BaseDTO
        , BO extends BaseBO
        , DO extends BaseDO
        , Convert extends BaseConvert<QueryRes, AddReq, QueryReq, BO, DO>
        , DomainService extends BaseDomainService<BO>
        > {


    protected Convert baseConvert;

    protected DomainService baseDomainService;

    /**
     * 分页查询
     *
     * @param queryReq 查询条件
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 分页结果
     */

    @GetMapping("/selectPage")
    public Result<Page<QueryRes>> selectPage(@ModelAttribute("entity") QueryReq queryReq,
                                             @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                             @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        Page<BO> PageList = baseDomainService.getPageList(baseConvert.convertQueryDTO2BO(queryReq), pageNum, pageSize);
        return Result.ok(baseConvert.convertPageBO2DTO(PageList));
    }

    /**
     * 添加
     *
     * @param addReq 添加信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<Object> add(@RequestBody @Validated(Groups.Add.class) AddReq addReq) {
        baseDomainService.add(baseConvert.convertDTO2BO(addReq));
        return Result.okMessage("添加成功");
    }

    /**
     * 更新
     *
     * @param addReq 更新信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody @Validated(Groups.Update.class) AddReq addReq) {
        baseDomainService.update(baseConvert.convertDTO2BO(addReq));
        return Result.okMessage("修改成功");
    }

    /**
     * 删除
     *
     * @param ids 删除id
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public Result<Object> delete(@RequestBody Long[] ids) {
        baseDomainService.delete(ids);
        return Result.okMessage("删除成功");
    }


}
