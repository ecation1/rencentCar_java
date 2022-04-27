package com.example.demo.service;

import com.example.demo.pojo.Shop;

public interface ShopService {
    int addShop(Shop shop);
    Shop getShopInfo(String uid);
    int setShopInfo(Shop shop);
}
