package com.example.demo.mapper;

import com.example.demo.pojo.Brand;
import com.example.demo.pojo.Carver;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BrandMapper {
    List<Brand> selectBrand();
    int addBrand(Brand brand);
    int deleteBrand(String bid);
    int updateBrand(Brand brand);
    List<Carver> selectCarver();
}
