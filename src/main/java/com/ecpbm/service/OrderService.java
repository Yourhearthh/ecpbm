package com.ecpbm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.dao.mapper.OrderInfoMapper;
import com.ecpbm.dao.service.OrderInfoServiceImpl;
import com.ecpbm.dao.service.UserInfoServiceImpl;
import com.ecpbm.pojo.OrderInfo;
import com.ecpbm.pojo.ProductInfo;
import com.ecpbm.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderInfoServiceImpl orderInfoService;
    @Autowired
    UserInfoServiceImpl userInfoService;
    @Autowired
    OrderInfoMapper orderInfoMapper;

    /**
     * 订单分页列表
     * @param pageNum
     * @param pageSize
     * @param id
     * @param userName
     * @param status
     * @param orderTimeFrom
     * @param orderTimeTo
     * @return
     */
    public Page<OrderInfo> getOrderPage(Integer pageNum, Integer pageSize, String id, String userName, String status, String orderTimeFrom, String orderTimeTo) {
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (id != null) {
            queryWrapper.eq(OrderInfo::getId, id);
        }
        if (userName != null) {
            UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("userName", userName));
            queryWrapper.eq(OrderInfo::getUid, userInfo.getId());
        }
        if (status != null) {
            queryWrapper.eq(OrderInfo::getStatus, status);
        }
        if (orderTimeFrom != null) {
            queryWrapper.gt(OrderInfo::getOrdertime, orderTimeFrom);
        }
        if (orderTimeTo != null) {
            queryWrapper.lt(OrderInfo::getOrdertime, orderTimeTo);
        }
        Page<OrderInfo> infoPage = orderInfoService.page(new Page<OrderInfo>(pageNum, pageSize), queryWrapper);
        return infoPage;
    }

    /**
     * 订单分页列表
     * @param pageNum
     * @param pageSize
     * @param id
     * @param userName
     * @param status
     * @param orderTimeFrom
     * @param orderTimeTo
     * @return
     */
    public Page<OrderInfo> getOrderInfoPage(Integer pageNum, Integer pageSize, String id, String userName, String status, String orderTimeFrom, String orderTimeTo) {
        if (userName != null) {
            UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("userName", userName));
            int uid = userInfo.getId();
            Page<OrderInfo> orderInfoPage = orderInfoMapper.getOrderInfoPage(new Page<OrderInfo>(pageNum, pageSize), id, String.valueOf(uid), status, orderTimeFrom, orderTimeTo);
            return orderInfoPage;
        } else {
            String uid1 = null;
            Page<OrderInfo> orderInfoPage1 = orderInfoMapper.getOrderInfoPage(new Page<OrderInfo>(pageNum, pageSize), id, uid1, status, orderTimeFrom, orderTimeTo);
            return orderInfoPage1;
        }

    }

}
