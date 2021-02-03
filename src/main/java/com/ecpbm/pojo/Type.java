package com.ecpbm.pojo;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "商品类型")
public class Type {
    private int id; // 产品类型编号
    private String name; // 产品类型名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
