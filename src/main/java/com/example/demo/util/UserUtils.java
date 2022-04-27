package com.example.demo.util;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Role_User;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtils {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StpInterfaceImpl stpInterface;
    public boolean isHasUsername(String username){//查询用户名是否重复
        List<User> users = userMapper.isHasUsername(username);
        if(users.size()!=0){
            return true;
        }else {
            return false;
        }
    }
    public Role_User getUserRole(String uid){//查询用户连接信息
        Role_User role_user = userMapper.selectRoleUser(uid);
        return role_user;
    }
    public static SaLoginModel LoginConfig(String device,boolean isLastingCookie){
        SaLoginModel saLoginModel=new SaLoginModel();
        saLoginModel.setDevice(device);
        saLoginModel.setIsLastingCookie(isLastingCookie);
        return saLoginModel;
    }
}
