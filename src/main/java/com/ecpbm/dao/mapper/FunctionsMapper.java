package com.ecpbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecpbm.pojo.Functions;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionsMapper extends BaseMapper<Functions> {
    // 根据管理员id,获取功能权限
    @Select("select * from functions where id in (select fid from powers where aid = #{aid} )")
    public List<Functions> selectByAdminId(Integer aid);
}
