package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.pojo.Cars;
import com.example.demo.pojo.Order;
import com.example.demo.service.CarService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CarService carService;
    @RequestMapping("overOrder")
    @SaCheckPermission(orRole = {"user"})
    public SaResult addOrder(@RequestBody Order order) {
        order.setStatus(2);
        order.setOrderEndTime(new SimpleDateFormat("yyyy-MM-dd HH.mm").format(new Date()).toString());
        System.out.println(order);
        int i = orderService.updateOrder(order);
        Cars cars=new Cars();
        cars.setCid(order.getCid());
        cars.setStatus(1);
        carService.updateCar(cars);
        if(i==0){
            return SaResult.error("更新订单失败");
        }else {
            return SaResult.ok("更新订单成功");
        }
    }

    @RequestMapping("updateOrder")
    public SaResult updateOrder(@RequestBody Order order) {
        int i = orderService.updateOrder(order);
        if(i==0){
            return SaResult.error("更新订单失败");
        }else {
            return SaResult.ok("更新订单成功");
        }
    }

    @RequestMapping("deleteOrder")
    public SaResult deleteOrder(@RequestBody Order order) {
        int i = orderService.deleteOrder(order);
        if(i==0){
            return SaResult.error("删除订单失败");
        }else {
            return SaResult.ok("删除订单成功");
        }
    }

    @RequestMapping("getOrder")
    public SaResult selectOrder() {
        List<Order> orders = orderService.selectOrder(StpUtil.getLoginId().toString());
        return SaResult.get(200,"查询成功",orders);
    }

    @RequestMapping("getMyOrder")
    @SaCheckPermission(orRole = {"user"})
    public SaResult getMyOrder(@RequestBody Map map){
        System.out.println(map);
        map.put("uid",StpUtil.getLoginId().toString());
        List<Map> toBeOrder = orderService.getMyOrder(map);
        return SaResult.get(200,"查询成功",toBeOrder);
    }

    @RequestMapping("getShopOrder")
    @SaCheckPermission(orRole = {"shop"})
    public SaResult getShopOrder(){
        List<Map> shopOrder = orderService.getShopOrder();
        return SaResult.get(200,"",shopOrder);
    }
}
