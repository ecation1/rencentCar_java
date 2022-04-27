package com.example.demo.service.serviceImpl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.mapper.CarsMapper;
import com.example.demo.mapper.ShopMapper;
import com.example.demo.pojo.Cars;
import com.example.demo.pojo.Shop;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarsMapper carsMapper;
    @Autowired
    ShopMapper shopMapper;
    @Override
    public List<Map> selectCarByShop(String uid) {
        return carsMapper.selectCarByShop(uid);
    }

    @Override
    public List<Map> selectToBeCar() {
        return carsMapper.selectToBeCar();
    }

    @Override
    public List<Map> getCarMenu(Map map) {
        return carsMapper.getCarMenu(map);
    }

    @Override
    @Transactional
    public int addToBeCar(Cars cars) {
        cars.setCid(UUID.randomUUID().toString());
        int i = carsMapper.addToBeCar(cars);
        Map map=new HashMap();
        map.put("csid", UUID.randomUUID().toString());
        map.put("cid",cars.getCid());
        Shop shopInfo = shopMapper.getShopInfo(StpUtil.getLoginId().toString());
        map.put("sid",shopInfo.getSid());
        return carsMapper.addCar_Shop(map);
    }

    @Override
    public int accessCar(Cars cars) {
        cars.setStatus(1);
        return carsMapper.accessCar(cars);
    }

    @Override
    public int updateCar(Cars cars) {
        return carsMapper.updateCar(cars);
    }

    @Override
    @Transactional
    public int deleteCar(Cars cars) {
        int i = carsMapper.deleteCar_Shop(cars.getCid());
        if(i==0){
            return -1;
        }else {
            return carsMapper.deleteCar(cars);
        }
    }
}
