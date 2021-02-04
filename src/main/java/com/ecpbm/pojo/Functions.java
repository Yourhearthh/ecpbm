package com.ecpbm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@TableName("functions" )
public class Functions implements Comparable<Functions> {

    @ApiModelProperty(value = "主键")
    @TableId
    private int id;

    @ApiModelProperty(value = "权限名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "父节点")
    @TableField("parentid")
    private int parentid;

    @ApiModelProperty(value = "是否子节点")
    @TableField("isleaf")
    private boolean isleaf;



    @Override
    public int compareTo(Functions arg0) {
        return ((Integer) this.getId()).compareTo((Integer) (arg0.getId()));
    }
}
