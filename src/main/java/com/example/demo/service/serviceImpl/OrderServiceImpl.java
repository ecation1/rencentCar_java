package com.example.demo.service.serviceImpl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.mapper.CarsMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.pojo.Cars;
import com.example.demo.pojo.Order;
import com.example.demo.service.OrderService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CarsMapper carsMapper;

    @Override
    public Order getOrder(String oid) {
        return orderMapper.getOrder(oid);
    }

    @Override
    public int addOrder(Order order) {
        order.setOrderStartTime(new SimpleDateFormat("yyyy-MM-dd HH.mm").format(new Date()).toString());
        return orderMapper.addOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        System.out.println("order-update:   "+order);
        return orderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrder(Order order) {
        return orderMapper.deleteOrder(order);
    }

    @Override
    public List<Order> selectOrder(String uid) {
        return orderMapper.selectOrder(uid);
    }

    @Override
    public List<Map> getMyOrder(Map map) {
        return orderMapper.getMyOrder(map);
    }

    @Override
    public List<Map> getShopOrder() {
        return orderMapper.getShopOrder(StpUtil.getLoginId().toString());
    }
}
