package com.ecpbm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.dao.mapper.OrderDetailMapper;
import com.ecpbm.dao.mapper.ProductInfoMapper;
import com.ecpbm.dao.mapper.TypeMapper;
import com.ecpbm.dao.service.ProductInfoServiceImpl;
import com.ecpbm.pojo.OrderDetail;
import com.ecpbm.pojo.ProductInfo;
import com.ecpbm.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    OrderDetailMapper orderDetailMapper;

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
    @Transactional
    public void addProduct(ProductInfo productInfo, MultipartFile file, HttpServletRequest request) {
        // 服务器端upload文件夹物理路径
        String path = request.getSession().getServletContext().getRealPath("product_images");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 实例化一个File对象，表示目标文件（含物理路径）
        assert fileName != null;
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 将上传文件写到服务器上指定的文件
        try {
            file.transferTo(targetFile);
            productInfo.setPic(fileName);
            productInfoMapper.addProductInfo(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取在售商品
     * @param status
     * @return
     */
    public List<ProductInfo> getSaleOnProduct(Integer status) {
        List<ProductInfo> productInfoList = productInfoService.list(new QueryWrapper<ProductInfo>().eq("status", status));
        return productInfoList;
    }

    /**
     * 根据id删除该商品
     * @param ids
     */
    @Transactional
    public void deleteProductById(String ids) {
        List<String> list = new ArrayList<>();
        String[] str = ids.split(",");
        Collections.addAll(list, str);
//        List<OrderDetail> details = orderDetailMapper.selectList(new QueryWrapper<OrderDetail>().in("pid", list));
        productInfoMapper.deleteProductById(list);
    }
}
