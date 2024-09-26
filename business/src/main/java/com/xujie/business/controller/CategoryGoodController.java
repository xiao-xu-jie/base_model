package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.DTO.req.CategoryGoodQueryReqDTO;
import com.xujie.business.DTO.res.CategoryGoodQueryResDTO;
import com.xujie.business.common.entity.Result;
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


    @GetMapping("/list")
    public Result<List<CategoryGoodQueryResDTO>> list(@ModelAttribute CategoryGoodQueryReqDTO categoryGoodQueryReqDTO) {

        return Result.okMessage("待开发");
    }
}
