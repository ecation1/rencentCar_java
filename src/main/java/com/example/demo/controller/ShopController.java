package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.pojo.Shop;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
public class ShopController {
    @Autowired
    ShopService shopService;
    @SaCheckPermission(value = "shop-select",orRole = {"shop","admin"})
    @RequestMapping("getShopInfo")
    public SaResult getShopInfo(){
        Shop shopInfo = shopService.getShopInfo(StpUtil.getLoginId().toString());
        return SaResult.data(shopInfo);
    }
    @SaCheckPermission(value = "shop-update",orRole = {"shop","admin"})
    @RequestMapping("setShopInfo")
    public SaResult setShopInfo(@RequestBody Shop shop){
        shop.setUid(StpUtil.getLoginId().toString());
        int i = shopService.setShopInfo(shop);
        if(i==0){
            return SaResult.error("商店信息更新失败");
        }else {
            return SaResult.ok("更新成功");
        }
    }
}
