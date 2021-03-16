package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecpbm.pojo.AdminInfo;
import com.ecpbm.pojo.LoginVo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminInfoMapper extends BaseMapper<AdminInfo> {
    // 根据管理员id获取管理员对象及关联的功能集合
    @Select("select * from admin_info where id = #{id}")
    @Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "name", property = "name"),
            @Result(column = "pwd", property = "pwd"),
            @Result(column = "id", property = "fs", many = @Many(select = "com.ecpbm.dao.mapper.FunctionsMapper.selectByAdminId", fetchType = FetchType.EAGER)) })
    AdminInfo selectById(Integer id);

    // 根据登录名和密码查询管理员
    @Select("select * from admin_info where name = #{name} and pwd = #{pwd}")
    AdminInfo selectByNameAndPwd(LoginVo ai);
}
