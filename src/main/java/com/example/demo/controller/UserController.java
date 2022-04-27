package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

import com.example.demo.pojo.*;
import com.example.demo.service.ShopService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    //单用户相关
    @RequestMapping("userless")
    @SaCheckPermission(orRole = {"user","admin","shop"})
    public SaResult UserLess(){
        System.out.println("userless");
        return SaResult.ok("");
    }
        //用户注册
    @PostMapping("register")
    public SaResult register(@RequestBody User user){
        if(user.getStatus()==2){
            int num = userService.register(user);
            if(num==-1){
                return SaResult.error("用户名已存在");
            }else if (num==0){
                return SaResult.error("注册失败");
            }else if (num==-2){
                return SaResult.error("初始权限赋予失败");
            }else {
                return SaResult.ok("注册成功");
            }
        }else if(user.getStatus()==3){
            int num = userService.addTobeShop(user);
            if(num==-1){
                return SaResult.error("用户名已存在");
            }else if (num==-2){
                return SaResult.error("注册失败");
            }else {
                return SaResult.ok("请等待管理员审核");
            }
        }
        return SaResult.error("未知错误");
    }

      //用户登录
    @RequestMapping("/login")
    public SaResult login(User user){
//        System.out.println(map);
//        User user= (User) map.get("user");
        System.out.println(user);
        int login = userService.login(user);
        if(login==-1){
            return SaResult.error("账户或密码错误");
        }else if (login==0){
            return SaResult.error("账户已被禁用,请联系管理员");
        }else {
            System.out.println("getTokenValue()  "+StpUtil.getTokenValue());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            Map map=new HashMap();
            map.put("userInfo",user.getUsername());
            map.put("tokenInfo",StpUtil.getTokenInfo());
            map.put("role",StpUtil.getRoleList());
            return SaResult.get(200,"登录成功",map);
        }
    }
    //用户信息更新
    @PostMapping("updateUser")
    public SaResult updateUser(@RequestBody User user){
        System.out.println(user);
        int i = userService.updateUser(user);
        if(i==0){
            return SaResult.error("更新失败");
        }else {
            return SaResult.ok("更新成功");
        }
    }

    @RequestMapping("updatePass")
    public SaResult updatePass(@RequestBody Map map){
        int i = userService.updatePass(map);
        if(i==-1){
            return SaResult.error("旧密码错误");
        }else if (i==0){
            return SaResult.error("更新失败");
        }else {
            return SaResult.ok("更新成功");
        }
    }
    //查询待审核商家
    @SaCheckPermission(value = "admin-select",orRole = "admin")
    @RequestMapping("getToBeShop")
    public SaResult getToBeShop(){
        List<User> toBeShop = userService.getToBeShop();
        return SaResult.get(200,"查询成功",toBeShop);
    }

    @RequestMapping("logout")
    public SaResult logout(){
        StpUtil.logout(StpUtil.getLoginId());
        return SaResult.ok("注销成功");
    }

    @SaCheckPermission(value = "admin-delete",orRole = "admin")
    @RequestMapping("agreeToBe")
    public SaResult agreeToBe(@RequestBody User user){
        int num = userService.deleteToBeShop(user);
        int num2 = userService.agreeToBe(user);
        Shop shop=new Shop();
        shop.setUid(user.getUid());
        shopService.addShop(shop);
        if(num==0){
            return SaResult.error("待审核移除异常");
        }else {
            if(num2==0){
                return SaResult.error("商家通过异常");
            }else {
                return SaResult.ok("商家审核成功");
            }
        }
    }

    @SaCheckPermission(value = "admin-delete",orRole = "admin")
    @RequestMapping("deleteToBe")
    public SaResult deleteToBeShop(@RequestBody User user){
        int num = userService.deleteToBeShop(user);
        if(num==0){
            return SaResult.error("待审核移除异常");
        }else{
            return SaResult.ok("退回成功");
        }
    }
    //单权限相关
    @RequestMapping("getAllPermission")
    @SaCheckPermission(value = "admin-select",orRole = "admin")
    public SaResult selectAllPermission(){//查询所有权限
        List<Permission> permissions = userService.selectAllPermission();
        return SaResult.get(SaResult.CODE_SUCCESS,"查询成功",permissions);
    }
        //新增权限
    @SaCheckPermission("admin-add")
    @PostMapping("addpermisson")
    public SaResult AddPermission(Permission permission){
        int num = userService.AddPermission(permission);
        if(num==0){
            return SaResult.error("添加失败");
        }else {
            return SaResult.ok("添加成功");
        }
    }

    //单身份相关
    @RequestMapping("getAllRoles")
    @SaCheckPermission(value = "admin-select",orRole = "admin")
    public SaResult selectAllRoles(){//查询所有角色
        List<Roles> rolesList = userService.selectAllRoles();
        return SaResult.get(SaResult.CODE_SUCCESS,"查询成功",rolesList);
    }

    @SaCheckPermission(value = "admin-add",orRole = "admin")
    @RequestMapping("addrole")
    public SaResult addRole(Roles roles){//新增身份
        int num = userService.addRole(roles);
        if(num==0){
            return SaResult.error("新增失败");
        }else {
            return SaResult.ok("新增成功");
        }
    }
    //用户身份相关
    @RequestMapping("updateUserRole")
    @SaCheckPermission(value = "admin-update",orRole = "admin")
    public SaResult updateRole_User(@RequestBody Role_User role_user){//用户身份修改
        int num =userService.updateRole_User(role_user);
        if(num==0){
            return SaResult.error("更新失败");
        }else {
            return SaResult.ok("更新成功");
        }
    }
    @PostMapping("deleteUser")
    @SaCheckPermission(value = "admin-delete",orRole = "admin")
    public SaResult deleteUser(@RequestBody User user){
        System.out.println(user);
        int i = userService.deleteUser(user);
        if(i==-1){
            return SaResult.error("用户身份连接删除失败");
        }else if(i==0){
            return SaResult.error("用户删除失败");
        }else {
            return SaResult.ok("删除成功");
        }
    }

    //用户权限相关
    @SaCheckPermission("admin-select")
    @GetMapping(value = "getUserList/{pageNum}/{pageSize}")
    public SaResult selectAllUser(Map map, @PathVariable("pageSize") int pageSize, @PathVariable("pageNum") int pageNum){
        PageInfo<User> userPageInfo = userService.selectAllUser(map, pageSize, pageNum);
        System.out.println("userpageinfo       "+userPageInfo);
        return SaResult.get(SaResult.CODE_SUCCESS,"查询成功",userPageInfo);
    }

    @RequestMapping("isLogin") //查询登录状态
    public SaResult isLogin(HttpServletRequest request) {
        String satoken = request.getHeader("satoken");
        System.out.println(satoken);
        System.out.println("LoginType()  "+ StpUtil.getLoginType());
        System.out.println("LoginId()  "+StpUtil.getLoginId());
        System.out.println("getTokenName()  "+StpUtil.getTokenName());
        System.out.println("getLoginIdAsString()  "+StpUtil.getLoginIdAsString());
        System.out.println("getTokenValue()  "+StpUtil.getTokenValue());
        System.out.println("getLoginDevice()  "+StpUtil.getLoginDevice());
        System.out.println("getPermissionList()  "+StpUtil.getPermissionList());
        System.out.println("getRoleList"+StpUtil.getRoleList());
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }
    @RequestMapping("userInfo")
    public SaResult getUserInfo(){
        Map map=new HashMap();
        map.put("uid",StpUtil.getLoginId());
        PageInfo<User> userPageInfo = userService.selectAllUser(map, 6, 1);
        List<User> list = userPageInfo.getList();
        return SaResult.get(200,"查询成功",list.get(0));
    }
    //驾照相关
    @RequestMapping("addLicense")
    public SaResult addLicense(@RequestBody Map map){
//        System.out.println(map);
        License license=new License();
        license.setLid(UUID.randomUUID().toString());
        license.setLicenseId(map.get("LicenseId").toString());
        license.setLicensePic((String) map.get("licensePic"));
        license.setStatus(0);
        System.out.println(license.getLicenseId());
        license.setLid(UUID.randomUUID().toString());
        license.setUid(StpUtil.getLoginId().toString());
        int num = userService.addLicense(license);
        if(num==0){
            return SaResult.error("操作失败");
        }else {
            return SaResult.ok("操作成功");
        }
    }
    @RequestMapping("getLicense")
    public SaResult getLicense(){
        License license = userService.getLicense(StpUtil.getLoginId().toString());
        if(license==null){
            return SaResult.get(200,"无驾照信息",null);
        }
        return SaResult.get(200,"已查询到信息",license);
    }
    @SaCheckPermission(value = "admin-select",orRole = "admin")
    @RequestMapping("allLicense")
    public SaResult selectAllLicense(){
        List<Map> maps = userService.selectAllLicense();
        return SaResult.data(maps);
    }

    @SaCheckPermission(value = "admin-update",orRole = "admin")
    @RequestMapping("accessLicen")
    public SaResult accessLicen(@RequestBody Map map){
        int num = userService.accessLicen(map.get("lid").toString());
        if(num==0){
            return SaResult.error("操作失败");
        }else {
            return SaResult.ok("操作成功");
        }
    }
    @SaCheckPermission(value = "admin-delete",orRole = "admin")
    @RequestMapping("deleteLicen")
    public SaResult deleteLicen(@RequestBody Map map){//退回驾照
        int num = userService.deleteLicen(map.get("lid").toString());
        if(num==0){
            return SaResult.error("操作失败");
        }else {
            return SaResult.ok("操作成功");
        }
    }
}
