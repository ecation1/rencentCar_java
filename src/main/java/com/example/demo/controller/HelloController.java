package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.example.demo.util.DeviceUtils;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    @SaCheckPermission(value = {"user-add","user-none"},mode = SaMode.OR)
    @RequestMapping("hello")
    public String hello(Device device){
        return "hello spring "+ DeviceUtils.getDevice(device);
    }
    @RequestMapping("test")
    public SaResult test(@RequestBody Map map){
        System.out.println(map);
        String json= JSON.toJSONString(map.get("fileList"));
        System.out.println(json);

//        System.out.println(fileList.get(0).getUrl());
        return null;
    }
}
