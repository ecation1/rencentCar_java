package com.example.demo.service.serviceImpl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.UserService;
import com.example.demo.util.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserUtils userUtils;
    //单用户相关
    @Override
    @Transactional
    public int register(User user) {        //用户注册
        boolean hasUsername = userUtils.isHasUsername(user.getUsername());
        while (hasUsername==true){
            return -1;
        }
        System.out.println();
        user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH.mm").format(new Date()).toString());
        user.setUid(UUID.randomUUID().toString());
        System.out.println("userInfo:"+user);
        int num = userMapper.register(user);
        Role_User role_user=new Role_User();
        role_user.setRuid(UUID.randomUUID().toString());
        role_user.setUid(user.getUid());
        role_user.setRid("81940dcc-7fa5-451f-9b6f-4b9840eeee95");
        userMapper.addRole_User(role_user);
        return num;
    }

    public Role_User getInfo(){
        return userUtils.getUserRole(StpUtil.getLoginId().toString());
    }
    @Override
    @Transactional
    public int login(User user) {     //用户登录
        User login = userMapper.login(user);
        if (login==null){
            return -1;
        }else if (login.getStatus()!=0){
            return 0;
        }else {
            StpUtil.login(login.getUid(),UserUtils.LoginConfig("",true));
            return 1;
        }
    }

    @Override
    public int updateUser(User user) {
        int i = userMapper.updateUser(user);
        return i;
    }

    @Transactional
    public int addTobeShop(User user){ //添加代审核商家
        user.setUid(UUID.randomUUID().toString());
        boolean hasUsername = userUtils.isHasUsername(user.getUsername());
        while (hasUsername==true){
            return -1;
        }
        int i = userMapper.addTobeShop(user);
        if(i==0){
            return -2;
        }else {
            return 0;
        }
    }

    public List<User> getToBeShop(){   //查询待审核商家
        return userMapper.getToBeShop();
    }

    public int deleteToBeShop(User user){  //删除待审核商家
        return userMapper.deleteToBeShop(user);
    }

    @Transactional
    public int agreeToBe(User user) {        //通过商家审核
        user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH.mm").format(new Date()).toString());
        user.setUid(UUID.randomUUID().toString());
        System.out.println("userInfo:"+user);
        int num = userMapper.register(user);
        Role_User role_user=new Role_User();
        role_user.setRuid(UUID.randomUUID().toString());
        role_user.setUid(user.getUid());
        role_user.setRid("cdeb0906-3933-4a48-938d-29eee180306c");
        userMapper.addRole_User(role_user);
        return num;
    }

    @Override
    public int updatePass(Map map) {
        Map map1=new HashMap();
        map1.put("uid",StpUtil.getLoginId().toString());
        List<User> list = userMapper.selectAllUser(map1);
        User user=list.get(0);
        System.out.println(map);
        System.out.println("mypass:"+user.getPassword());
        if(!map.get("currentPass").equals(user.getPassword())){
            return -1;
        }else {
            user.setPassword((String) map.get("password"));
            return userMapper.updateUser(user);
        }
    }


    //单权限相关
    public List<Permission> selectAllPermission(){//查询所有权限
        List<Permission> permissions = userMapper.selectAllPermission();
        return permissions;
    }
    @Override
    public int AddPermission(Permission permission) {//新增权限
        int num = userMapper.AddPermission(permission);
        return num;
    }

    //单身份相关
    public List<Roles> selectAllRoles(){//查询所有角色
        List<Roles> rolesList = userMapper.selectAllRoles();
        return rolesList;
    }
     public int addRole(Roles roles){//新增身份
        roles.setRid(UUID.randomUUID().toString());
         int num = userMapper.addRole(roles);
         return num;
     }
    //用户身份相关
    public int updateRole_User(Role_User role_user){//用户身份修改
        Role_User userRole = userUtils.getUserRole(role_user.getUid());
        userRole.setRid(role_user.getRid());
        int num = userMapper.updateRole_User(userRole);
        return num;
    }
    @Transactional
    public int deleteUser(User user){//删除用户及其身份连接
        int num1 = userMapper.deleteUser_Roles(user.getUid());
        if(num1==0){
            return -1;
        }
        int i = userMapper.deleteUser(user);
        return i;
    }
    //用户权限相关

    @Override
    public PageInfo<User> selectAllUser(Map map, int pageSize, int pageNum) {//用户信息展示
        PageHelper.startPage(pageNum,pageSize);
        List<User> list=userMapper.selectAllUser(map);
        PageInfo<User> userPageInfo=new PageInfo<User>(list);
        return userPageInfo;
    }

    //驾照相关
     public int addLicense(License license){
        return userMapper.addLicense(license);
     }

    public License getLicense(String uid){
        return userMapper.getLicense(uid);
    }

    public List<Map> selectAllLicense(){
        return userMapper.selectAllLicense();
    }

    public int accessLicen(String lid){//驾照通过审核
        return userMapper.accessLicen(lid);
    }
    public int deleteLicen(String lid){//退回驾照
        return userMapper.deleteLicen(lid);
    }
}
