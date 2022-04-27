package com.example.demo.service;


import com.example.demo.pojo.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
    //单用户相关
    int register(User user);//用户注册
    int login(User user);//用户登录
    int updateUser(User user);//用户更新
    int addTobeShop(User user);//添加代审核商家
    List<User> getToBeShop();//查询待审核商家
    int deleteToBeShop(User user);//删除待审核商家
    int agreeToBe(User user);//通过商家审核
    int updatePass(Map map);
    //单权限相关
    List<Permission> selectAllPermission();//查询所有权限
    int AddPermission(Permission permission);//新增权限
    //单身份相关
    List<Roles> selectAllRoles();//查询所有角色
    int addRole(Roles roles);//新增身份
    //用户身份相关
    int deleteUser(User user);//删除用户及其身份连接
    int updateRole_User(Role_User role_user);//用户身份修改
    //用户权限相关

    PageInfo<User> selectAllUser(Map map,int pageSize,int pageNum);//用户展示


    //驾照相关
    int addLicense(License license);
    License getLicense(String uid);
    List<Map> selectAllLicense();
    int accessLicen(String lid);//驾照通过审核
    int deleteLicen(String lid);//退回驾照
}
