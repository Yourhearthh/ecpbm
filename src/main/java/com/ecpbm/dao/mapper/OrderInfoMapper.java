package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.pojo.OrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    Page<OrderInfo> getOrderInfoPage(Page<OrderInfo> orderInfoPage, String id, String uid, String status, String orderTimeFrom, String orderTimeTo);

    void deleteOrder(List<String> stringList);
}
