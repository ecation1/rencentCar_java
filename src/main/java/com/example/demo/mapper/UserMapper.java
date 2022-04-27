package com.example.demo.mapper;

import com.example.demo.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    //单用户相关
    int register(User user);//用户注册
    User login(User user);//用户登录
    int deleteUser(User user);//删除用户
    List<User> isHasUsername(String username);//查询用户名是否存在
    int updateUser(User user);//用户更新
    int addTobeShop(User user);//添加代审核商家
    List<User> getToBeShop();//查询待审核商家
    int deleteToBeShop(User user);
    User getUserInfo(String username);
    //单权限相关
    List<Permission> selectAllPermission();//查询所有权限
    int AddPermission(Permission permission);//新增权限
    //单身份相关
    List<Roles> selectAllRoles();
    int addRole(Roles roles);//新增身份
    //用户身份相关
    int addRole_User(Role_User role_user);//给用户赋予身份
    Roles selectRole(String uid);//检验用户身份
    int deleteUser_Roles(String uid);//删除用户身份连接
    Role_User selectRoleUser(String uid);//查询用户身份连接信息
    int updateRole_User(Role_User role_user);//用户身份修改
    //用户权限相关
    List<User> selectAllUser(Map map);//获取所有用户及附属信息
    List<Permission> selectPermisson(String uid);//查询用户权限

    //驾照相关
    int addLicense(License license);
    License getLicense(String uid);
    List<Map> selectAllLicense();
    int accessLicen(String lid);//驾照通过审核
    int deleteLicen(String lid);//退回驾照
}
