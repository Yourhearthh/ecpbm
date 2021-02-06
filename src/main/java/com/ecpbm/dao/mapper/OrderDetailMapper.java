package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecpbm.pojo.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    void deleteOrderDetail(List<String> stringList);
}
