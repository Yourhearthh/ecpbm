package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.pojo.Type;
import com.ecpbm.pojo.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    Page<UserInfo> getUserInfoPage(String userName, String sex, Page<UserInfo> dtoPage);
}
