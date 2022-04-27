package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.AdvertMapper;
import com.example.demo.pojo.Advert;
import com.example.demo.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    AdvertMapper advertMapper;
    @Override
    public List<Advert> selectAdvert() {
        return advertMapper.selectAdvert();
    }

    @Override
    public int addAdvert(Advert advert) {
        advert.setAid(UUID.randomUUID().toString());
        return advertMapper.addAdvert(advert);
    }

    @Override
    public int deleteAdvert(String aid) {
        return advertMapper.deleteAdvert(aid);
    }

    @Override
    public int updateAdvert(Advert advert) {
        return advertMapper.updateAdvert(advert);
    }
}
