package com.example.demo.util;

import cn.dev33.satoken.stp.StpInterface;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Roles;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Permission> permissions = userMapper.selectPermisson((String) loginId);
        List<String> list = new ArrayList<String>();
        for (Permission permission : permissions) {
            list.add(permission.getPermission());
        }
        return list;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Roles roles = userMapper.selectRole((String) loginId);
        List<String> list = new ArrayList<String>();
        list.add(roles.getRole());
        for (String s : list) {
            System.out.println(s);
        }
        return list;
    }
}
