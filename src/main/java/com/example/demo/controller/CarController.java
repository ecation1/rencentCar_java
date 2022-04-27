package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.pojo.Cars;
import com.example.demo.pojo.Shop;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping("getMyCar")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult selectCarByShop() {
        List<Map> maps = carService.selectCarByShop(StpUtil.getLoginId().toString());
        return SaResult.get(200,"查询成功",maps);
    }

    @RequestMapping("getToBeCar")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult selectToBeCar() {
        List<Map> maps = carService.selectToBeCar();
        return SaResult.get(200,"查询成功",maps);
    }

    @RequestMapping("getCarMenu")
    public SaResult getCarMenu(@RequestBody Map map){
        List<Map> carMenu = carService.getCarMenu(map);
        return SaResult.get(200,"查询成功",carMenu);
    }

    @RequestMapping("addToBeCar")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult addToBeCar(@RequestBody Cars cars) {
        int i = carService.addToBeCar(cars);
        if(i==-1){
            return SaResult.error("汽车新增失败");
        }else if (i==0){
            return SaResult.error("汽车商店连接失败");
        }else {
            return SaResult.ok("请等待审核");
        }
    }

    @RequestMapping("accessCar")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult accessCar(@RequestBody Cars cars) {
        int i = carService.accessCar(cars);
        if(i==0){
            return SaResult.error("审核通过失败");
        }else {
            return SaResult.ok("审核通过");
        }
    }

    @RequestMapping("updateCar")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult updateCar(@RequestBody Cars cars) {
        int i = carService.updateCar(cars);
        if(i==0){
            return SaResult.error("更新失败");
        }else {
            return SaResult.ok("更新通过");
        }
    }

    @RequestMapping("deleteCar")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult deleteCar(@RequestBody Cars cars) {
        int i = carService.deleteCar(cars);
        if(i==-1){
            return SaResult.error("汽车商店连接删除失败");
        }else if (i==0){
            return SaResult.error("汽车删除失败");
        }else {
            return SaResult.ok("删除成功");
        }
    }
}
