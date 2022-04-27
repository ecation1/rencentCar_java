package com.example.demo.mapper;

import com.example.demo.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    int addShop(Shop shop);
    Shop getShopInfo(String uid);
    int setShopInfo(Shop shop);
}
