package com.ecpbm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("order_detail" )
public class OrderDetail implements Serializable {

    private static final long serialVersionUID =  1495331397904264136L;

    @ApiModelProperty(value = "主键")
    @TableId
    private int id;

    @ApiModelProperty(value = "订单id")
    @TableField("oid")
    private int oid;

    @ApiModelProperty(value = "商品id")
    @TableField("pid")
    private int pid;

    @ApiModelProperty(value = "商品数量")
    @TableField("num")
    private int num;

}
