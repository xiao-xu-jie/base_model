package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.DTO.req.CategoryGoodQueryReqDTO;
import com.xujie.business.DTO.res.CategoryGoodQueryResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.domain.BO.CategoryGoodBO;
import com.xujie.business.domain.convert.CategoryGoodConvert;
import com.xujie.business.domain.service.CategoryGoodDomainService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品控制器
 * @author Xujie
 * @since 2024/9/26 20:47
 **/
@SaIgnore
@RestController
@RequestMapping("/categoryGood")
public class CategoryGoodController {
    @Resource
    private CategoryGoodDomainService baseService;
    @Resource
    private CategoryGoodConvert categoryGoodConvert;

    /**
     * 分类商品列表
     * @param categoryGoodQueryReqDTO 分类商品查询请求DTO
     * @return 分类商品列表
     */
    @GetMapping("/list")
    public Result<List<CategoryGoodQueryResDTO>> list(@ModelAttribute CategoryGoodQueryReqDTO categoryGoodQueryReqDTO) {
        List<CategoryGoodBO> categoryListByEntity = baseService.getCategoryListByEntity(null);

        return Result.ok(categoryGoodConvert.convertCGBO2DTO(categoryListByEntity));
    }

}
