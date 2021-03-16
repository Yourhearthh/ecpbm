package com.ecpbm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecpbm.dao.mapper.AdminInfoMapper;
import com.ecpbm.dao.mapper.UserInfoMapper;
import com.ecpbm.dao.service.AdminInfoServiceImpl;
import com.ecpbm.dao.service.FunctionsServicesImpl;
import com.ecpbm.dao.service.UserInfoServiceImpl;
import com.ecpbm.pojo.AdminInfo;
import com.ecpbm.pojo.Functions;
import com.ecpbm.pojo.TreeNode;
import com.ecpbm.pojo.UserInfo;
import com.ecpbm.utils.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserInfoServiceImpl userInfoService;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    AdminInfoServiceImpl adminInfoService;
    @Autowired
    FunctionsServicesImpl functionsService;
    @Autowired
    AdminInfoMapper adminInfoMapper;

    /**
     * 获取用户列表
     * @return
     */
    public List<UserInfo> getUsers() {
        return userInfoService.list();
    }

    /**
     * 分页获取用户列表
     * @return
     */
    public Page<UserInfo> getPage(Integer pageNum, Integer pageSize, String userName, String sex) {
        Page<UserInfo> dtoPage = new Page<>(pageNum, pageSize);
        return userInfoMapper.getUserInfoPage(userName, sex, dtoPage);
    }

    /**
     * 更新用户状态
     * @return
     */
    public void modifyStatus(String ids, int flag) {
        List<String> list = new ArrayList<>();
        String a []  = ids.split(",");
        for(int i=0;i<a.length;i++){
            list.add(a[i]);
        }
        userInfoMapper.updateState(list, flag);
    }

    /**
     * 获取管理员的权限树
     * @return
     */
    public List<TreeNode> getTree(Integer adminid) {
        //获取管理员
        AdminInfo adminInfo = adminInfoService.selectById(adminid);
        List<TreeNode> nodes = new ArrayList<>();
        //获取关联的Functions对象集合
//        List<Functions> functionsList = functionsService.list();
        List<Functions> functionsList = adminInfo.getFs();
                // 对List<Functions>类型的Functions对象集合排序
        Collections.sort(functionsList);
        // 将排序后的Functions对象集合转换到List<TreeNode>类型的列表nodes
        for (Functions functions : functionsList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(functions.getId());
            treeNode.setFid(functions.getParentid());
            treeNode.setText(functions.getName());
            nodes.add(treeNode);
        }
        // 调用自定义的工具类JsonFactory的buildtree方法，为nodes列表中的各个TreeNode元素中的
        // children属性赋值(该节点包含的子节点)
        List<TreeNode> treeNodes = JsonFactory.buildtree(nodes, 0);
        return treeNodes;
    }

    /**
     * 登录
     * @param ai
     * @return
     */
    public AdminInfo login(AdminInfo ai) {
        return adminInfoMapper.selectByNameAndPwd(ai);
    }
}
