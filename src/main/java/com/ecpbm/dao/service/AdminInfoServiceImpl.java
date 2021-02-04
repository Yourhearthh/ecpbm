package com.ecpbm.dao.service;

import com.ecpbm.dao.mapper.AdminInfoMapper;
import com.ecpbm.pojo.AdminInfo;
import com.ecpbm.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminInfoServiceImpl extends BaseServiceImpl<AdminInfoMapper, AdminInfo> {
    @Autowired
    AdminInfoMapper adminInfoMapper;

    public AdminInfo selectById(Integer aid) {
        return adminInfoMapper.selectById(aid);
    }
}
