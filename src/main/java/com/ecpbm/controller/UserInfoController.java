package com.ecpbm.controller;

import com.ecpbm.pojo.BaseResponse;
import com.ecpbm.pojo.PageResponse;
import com.ecpbm.pojo.ResultCode;
import com.ecpbm.pojo.UserInfo;
import com.ecpbm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户")
public class UserInfoController {
    @Autowired
    UserService userService;

    @ApiOperation("获取全部用户")
    @GetMapping("/getUsers")
    public BaseResponse<List<UserInfo>> getUsers() {
        List<UserInfo> userList = userService.getUsers();
        return BaseResponse.success(userList);
    }

    @ApiOperation("分页查询用户")
    @GetMapping("/pageUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户名称模糊查询", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别（男，女）", dataType = "String", paramType = "query")
    })
    public PageResponse<List<UserInfo>> pageUserInfo(
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize,
            @RequestParam(value = "userName",required = false) String userName,
            @RequestParam(value = "sex",required = false) String sex
    ) {
        return PageResponse.success(userService.getPage(pageNum, pageSize, userName, sex));
    }

    @ApiOperation("更新用户状态")
    @GetMapping("/setIsEnableUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uids", value = "用户id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "flag", value = "用户状态", dataType = "String", paramType = "query")
    })
    public BaseResponse setIsEnableUser(@RequestParam(value = "uids") String uids,
                                           @RequestParam(value = "flag") String flag
    ) {
        try {
            userService.modifyStatus(uids, Integer.parseInt(flag));
            return BaseResponse.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            return BaseResponse.success(ResultCode.FAILED);
        }

    }


}
