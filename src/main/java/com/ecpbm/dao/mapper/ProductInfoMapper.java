package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecpbm.pojo.ProductInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    void deleteProduct(List<String> ids, int flag);

    // 添加商品
    @Insert("insert into product_info(code,name,tid,brand,pic,num,price,intro,status) "
            + "values(#{code},#{name},#{tid},#{brand},#{pic},#{num},#{price},#{intro},#{status})")
    void addProductInfo(ProductInfo productInfo);

    void deleteProductById(List<String> list);
}
