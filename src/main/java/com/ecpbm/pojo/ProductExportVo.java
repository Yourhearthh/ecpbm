package com.ecpbm.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**商品导出类
 * @author baoguangyu
 * @version 1.0
 * @date 2021/3/15 14:31
 */
@Data
@ToString
public class ProductExportVo implements Serializable {

    private static final long serialVersionUID =  1495331397904264136L;

    @ApiModelProperty(value = "主键")
    private int id; // 商品编号

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品品牌")
    private String brand;

    @ApiModelProperty(value = "商品小图")
    private String pic;

    @ApiModelProperty(value = "商品数量")
    private int num;

    @ApiModelProperty(value = "商品价格")
    private double price;

    @ApiModelProperty(value = "商品介绍")
    private String intro;

    @ApiModelProperty(value = "商品状态（0，在售，1，下架）")
    private String status;
}
