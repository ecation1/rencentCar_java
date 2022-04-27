package com.example.demo.service;

import com.example.demo.pojo.Advert;

import java.util.List;

public interface AdvertService {
    List<Advert> selectAdvert();
    int addAdvert(Advert advert);
    int deleteAdvert(String aid);
    int updateAdvert(Advert advert);
}
