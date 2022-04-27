package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.ShopMapper;
import com.example.demo.pojo.Shop;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper shopMapper;
    @Override
    public int addShop(Shop shop) {
        shop.setSid(UUID.randomUUID().toString());
        return shopMapper.addShop(shop);
    }

    @Override
    public Shop getShopInfo(String uid) {
        return shopMapper.getShopInfo(uid);
    }

    @Override
    public int setShopInfo(Shop shop) {
        return shopMapper.setShopInfo(shop);
    }
}
