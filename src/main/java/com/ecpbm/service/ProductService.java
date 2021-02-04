package com.ecpbm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.dao.mapper.ProductInfoMapper;
import com.ecpbm.dao.mapper.TypeMapper;
import com.ecpbm.dao.service.ProductInfoServiceImpl;
import com.ecpbm.pojo.ProductInfo;
import com.ecpbm.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    ProductInfoServiceImpl productInfoService;
    @Autowired
    TypeMapper typeMapper;

    public Page<ProductInfo> getProductPage(Integer pageNum, Integer pageSize, String name, String code, String type, String brand, String priceFrom, String priceTo) {
        LambdaQueryWrapper<ProductInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null) {
            queryWrapper.like(ProductInfo::getName, name + "%");
        }
        if (code != null) {
            queryWrapper.eq(ProductInfo::getCode, code);
        }
        if (type != null) {
            Type type1 = typeMapper.selectOne(new QueryWrapper<Type>().eq("name", type));
            queryWrapper.eq(ProductInfo::getTid, type1.getId());
        }
        if (brand != null) {
            queryWrapper.like(ProductInfo::getBrand, brand + "%");
        }
        if (priceFrom != null) {
            queryWrapper.gt(ProductInfo::getPrice, priceFrom);
        }
        if (priceFrom != null) {
            queryWrapper.lt(ProductInfo::getPrice, priceTo);
        }
        Page<ProductInfo> infoPage = productInfoService.page(new Page<ProductInfo>(pageNum, pageSize), queryWrapper);
        return infoPage;
    }
}
