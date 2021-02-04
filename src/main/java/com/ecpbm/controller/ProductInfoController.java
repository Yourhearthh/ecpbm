package com.ecpbm.controller;

import com.ecpbm.pojo.PageResponse;
import com.ecpbm.pojo.ProductInfo;
import com.ecpbm.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productInfo")
@Api(tags = "商品")
public class ProductInfoController {

    @Autowired
    ProductService productService;

    @ApiOperation("分页查询商品")
    @GetMapping("/getProductPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "商品名称模糊查询", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "商品编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "商品类型", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "brand", value = "商品品牌", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "priceFrom", value = "大于商品价格", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "priceTo", value = "小于商品价格", dataType = "String", paramType = "query")
    })
    public PageResponse<List<ProductInfo>> getProductPage(
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "code",required = false) String code,
            @RequestParam(value = "type",required = false) String type,
            @RequestParam(value = "brand",required = false) String brand,
            @RequestParam(value = "priceFrom",required = false) String priceFrom,
            @RequestParam(value = "priceTo",required = false) String priceTo
    ) {
        return PageResponse.success(productService.getProductPage(pageNum, pageSize, name, code, type, brand, priceFrom, priceTo));
    }




}
