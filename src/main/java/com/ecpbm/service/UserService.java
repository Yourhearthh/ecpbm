package com.ecpbm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.dao.mapper.UserInfoMapper;
import com.ecpbm.dao.service.UserInfoServiceImpl;
import com.ecpbm.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserInfoServiceImpl userInfoService;
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 获取用户列表
     * @return
     */
    public List<UserInfo> getUsers() {
        return userInfoService.list();
    }

    /**
     * 分页获取用户列表
     * @return
     */
    public Page<UserInfo> getPage(Integer pageNum, Integer pageSize, String userName, String sex) {
        Page<UserInfo> dtoPage = new Page<>(pageNum, pageSize);
        return userInfoMapper.getUserInfoPage(userName, sex, dtoPage);
    }
}
