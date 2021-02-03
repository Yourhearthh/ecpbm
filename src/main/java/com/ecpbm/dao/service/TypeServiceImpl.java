package com.ecpbm.dao.service;

import com.ecpbm.dao.mapper.TypeMapper;
import com.ecpbm.pojo.Type;
import com.ecpbm.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeServiceImpl extends BaseServiceImpl<TypeMapper, Type> {
    @Autowired
    TypeMapper typeMapper;


    public int addType(Type type) {
        return typeMapper.addType(type);
    }
}
