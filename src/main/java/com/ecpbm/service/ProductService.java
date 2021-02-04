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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    ProductInfoServiceImpl productInfoService;
    @Autowired
    TypeMapper typeMapper;

    /**
     * 获取商品分页列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param code
     * @param type
     * @param brand
     * @param priceFrom
     * @param priceTo
     * @return
     */
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

    /**
     * 商品下架
     * @param ids
     * @param flag
     */
    public void deleteProduct(String ids, int flag) {
        List<String> list = new ArrayList<>();
        String[] str = ids.split(",");
        Collections.addAll(list, str);
        productInfoMapper.deleteProduct(list, flag);
    }

    /**
     * 根据商品id获取商品信息
     * @param id
     * @return
     */
    public ProductInfo getProductById(Integer id) {
        return productInfoMapper.selectById(id);
    }

    /**
     * 添加商品
     * @param productInfo
     * @return
     */
    public int addProduct(ProductInfo productInfo) {
        return 0;
    }
}
