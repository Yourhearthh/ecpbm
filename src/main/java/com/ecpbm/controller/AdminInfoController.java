package com.ecpbm.controller;

import com.ecpbm.dao.service.AdminInfoServiceImpl;
import com.ecpbm.pojo.*;
import com.ecpbm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "管理员")
public class AdminInfoController {
    @Autowired
    UserService userService;
    @Autowired
    AdminInfoServiceImpl adminInfoService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse login(AdminInfo ai) {
        AdminInfo adminInfo = userService.login(ai);
        ModelMap model = new ModelMap();
        try {
            if (adminInfo != null && adminInfo.getName() != null) {
                if (adminInfoService.selectById(adminInfo.getId()).getFs().size() >0) {
                    model.put("admin", adminInfo);
                }
            }
            return BaseResponse.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            return BaseResponse.errorWithException(ResultCode.FAILED, e);
        }

    }


    @ApiOperation("获取管理员登录的权限树")
    @GetMapping("/getTree")
    @ApiImplicitParam(name = "adminid", value = "管理员Id", dataType = "int", paramType = "query")
    public BaseResponse<List<TreeNode>> getTree(@RequestParam(value = "adminid") Integer adminid) {
        List<TreeNode> nodeList = userService.getTree(adminid);
        return BaseResponse.success(nodeList);
    }
}
