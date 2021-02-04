package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecpbm.pojo.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    void deleteProduct(List<String> ids, int flag);
}
