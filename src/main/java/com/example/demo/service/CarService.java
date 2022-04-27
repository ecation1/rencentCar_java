package com.example.demo.service;

import com.example.demo.pojo.Cars;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<Map> selectCarByShop(String uid);
    List<Map> selectToBeCar();
    List<Map> getCarMenu(Map map);//获取商品首页商品
    int addToBeCar(Cars cars);//新增待审核车辆
    int accessCar(Cars cars);//通过汽车审核
    int updateCar(Cars cars);//更新汽车资料
    int deleteCar(Cars cars);//删除汽车
}
