package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.pojo.Advert;
import com.example.demo.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("advert")
public class AdvertController {
    @Autowired
    AdvertService advertService;

    @RequestMapping("getAdvert")
    public SaResult selectAdvert() {
        List<Advert> adverts = advertService.selectAdvert();
        return SaResult.get(200,"查询成功",adverts);
    }

    @SaCheckPermission(orRole = "admin")
    @RequestMapping("addAdvert")
    public SaResult addAdvert(@RequestBody Advert advert) {
        int i = advertService.addAdvert(advert);
        if(i==0){
            return SaResult.error("新增失败");
        }else {
            return SaResult.ok("新增成功");
        }
    }

    @SaCheckPermission(orRole = "admin")
    @RequestMapping("deleteAdvert")
    public SaResult deleteAdvert(@RequestBody Advert advert) {
        int i = advertService.deleteAdvert(advert.getAid());
        if(i==0){
            return SaResult.error("删除失败");
        }else {
            return SaResult.ok("删除成功");
        }
    }

    @SaCheckPermission(orRole = "admin")
    @RequestMapping("updateAdvert")
    public SaResult updateAdvert(@RequestBody Advert advert) {
        int i = advertService.updateAdvert(advert);
        if(i==0){
            return SaResult.error("更新失败");
        }else {
            return SaResult.ok("更新成功");
        }
    }
}
