package com.ecpbm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


import java.io.Serializable;
import java.util.List;

@Data
@ToString
@TableName("admin_info" )
public class AdminInfo implements Serializable {

    private static final long serialVersionUID =  1495331397904264136L;

    @ApiModelProperty(value = "主键")
    @TableId
    private int id;

    @ApiModelProperty(value = "用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "密码")
    @TableField("pwd")
    private String pwd;

    // 关联的属性
    @ApiModelProperty(value = "权限")
    @TableField(exist = false)
    private List<Functions> fs;
}
