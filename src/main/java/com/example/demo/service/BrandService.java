package com.example.demo.service;

import com.example.demo.pojo.Brand;
import com.example.demo.pojo.Carver;

import java.util.List;

public interface BrandService {
    List<Brand> selectBrand();
    int addBrand(Brand brand);
    int deleteBrand(String bid);
    int updateBrand(Brand brand);
    List<Carver> selectCarver();
}
