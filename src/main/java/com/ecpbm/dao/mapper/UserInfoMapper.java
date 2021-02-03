package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.pojo.Type;
import com.ecpbm.pojo.UserInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    Page<UserInfo> getUserInfoPage(String userName, String sex, Page<UserInfo> dtoPage);

    // 更新客户状态
//    @Update("update user_info set status=#{flag} where id in (${ids})")
    void updateState(List<String> ids, int flag);
}
