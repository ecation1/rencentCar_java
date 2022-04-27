package com.example.demo.service;

import com.example.demo.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order getOrder(String oid);
    int addOrder(Order order);
    int updateOrder(Order order);
    int deleteOrder(Order order);
    List<Order> selectOrder(String uid);
    List<Map> getMyOrder(Map map);
    List<Map> getShopOrder();
}
