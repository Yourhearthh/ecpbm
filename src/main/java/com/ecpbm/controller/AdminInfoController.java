package com.ecpbm.controller;

import com.ecpbm.pojo.BaseResponse;
import com.ecpbm.pojo.TreeNode;
import com.ecpbm.pojo.UserInfo;
import com.ecpbm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "管理员")
public class AdminInfoController {
    @Autowired
    UserService userService;

    @ApiOperation("获取管理员登录的权限树")
    @GetMapping("/getTree")
    @ApiImplicitParam(name = "adminid", value = "管理员Id", dataType = "int", paramType = "query")
    public BaseResponse<List<TreeNode>> getTree(@RequestParam(value = "adminid") Integer adminid) {
        List<TreeNode> nodeList = userService.getTree(adminid);
        return BaseResponse.success(nodeList);
    }
}
