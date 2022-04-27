package com.example.demo.mapper;

import com.example.demo.pojo.Cars;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarsMapper {
    List<Map> selectCarByShop(String uid);
    List<Map> selectToBeCar();
    List<Map> getCarMenu(Map map);
    int addToBeCar(Cars cars);//新增待审核车辆
    int addCar_Shop(Map map);//新增汽车商店连接
    int accessCar(Cars cars);//通过汽车审核
    int updateCar(Cars cars);//更新汽车资料
    int deleteCar(Cars cars);//删除汽车
    int deleteCar_Shop(String cid);//删除汽车商店连接
}
