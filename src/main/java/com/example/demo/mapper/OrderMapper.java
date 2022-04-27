package com.example.demo.mapper;

import com.example.demo.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    int addOrder(Order order);
    int updateOrder(Order order);
    int deleteOrder(Order order);
    List<Order> selectOrder(String uid);
    Order getOrder(String oid);
    List<Map> getMyOrder(Map map);
    List<Map> getShopOrder(String uid);
}
