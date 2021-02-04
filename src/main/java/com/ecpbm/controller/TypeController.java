package com.ecpbm.controller;

import com.ecpbm.dao.service.TypeServiceImpl;
import com.ecpbm.pojo.BaseResponse;
import com.ecpbm.pojo.ResultCode;
import com.ecpbm.pojo.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@Api(tags = "商品类型")
public class TypeController {
    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/getType/{flag}")
    public List<Type> getType(@PathVariable("flag") Integer flag) {
        List<Type> typeList = typeService.list();
        if (flag == 1) {
            Type t = new Type();
            t.setId(0);
            t.setName("请选择...");
            typeList.add(0, t);
        }
        return typeList;
    }

    @ApiOperation("获取全部商品类型")
    @GetMapping("/getTypes")
    public BaseResponse<List<Type>> getTypes() {
        List<Type> typeList = typeService.list();
        return BaseResponse.success(typeList);
    }

    @ApiOperation("添加商品类型")
    @PostMapping("/addType")
    public BaseResponse<Type> addType(@RequestBody Type type) {
        int t = typeService.addType(type);
        if (t > 0) {
            return BaseResponse.success(ResultCode.SUCCESS);
        } else {
            return BaseResponse.success(ResultCode.FAILED);
        }
    }

}
