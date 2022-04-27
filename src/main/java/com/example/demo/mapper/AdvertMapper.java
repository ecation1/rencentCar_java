package com.example.demo.mapper;

import com.example.demo.pojo.Advert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertMapper {
    List<Advert> selectAdvert();
    int addAdvert(Advert advert);
    int deleteAdvert(String aid);
    int updateAdvert(Advert advert);
}
