package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.BrandMapper;
import com.example.demo.pojo.Brand;
import com.example.demo.pojo.Carver;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Brand> selectBrand() {
        return brandMapper.selectBrand();
    }

    @Override
    public int addBrand(Brand brand) {
        brand.setBid(UUID.randomUUID().toString());
        return brandMapper.addBrand(brand);
    }

    @Override
    public int deleteBrand(String bid) {
        return brandMapper.deleteBrand(bid);
    }

    @Override
    public int updateBrand(Brand brand) {
        return brandMapper.updateBrand(brand);
    }

    @Override
    public List<Carver> selectCarver() {
        return brandMapper.selectCarver();
    }
}
