package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.example.demo.pojo.Brand;
import com.example.demo.pojo.Carver;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("getBrand")
    @SaCheckPermission(value = "admin-select",orRole = {"admin","shop"})
    public SaResult selectBrand() {
        List<Brand> brands = brandService.selectBrand();
        return SaResult.get(200,"查询成功",brands);
    }

    @RequestMapping("addBrand")
    @SaCheckPermission(value = "admin-add",orRole = "admin")
    public SaResult addBrand(@RequestBody Brand brand) {
        int i = brandService.addBrand(brand);
        if(i==0){
            return SaResult.error("新增失败");
        }else {
            return SaResult.ok("新增成功");
        }
    }

    @RequestMapping("deleteBrand")
    @SaCheckPermission(value = "admin-delete",orRole = "admin")
    public SaResult deleteBrand(@RequestBody Brand brand) {
        int i = brandService.deleteBrand(brand.getBid());
        if(i==0){
            return SaResult.error("删除失败");
        }else {
            return SaResult.ok("删除成功");
        }
    }

    @RequestMapping("updateBrand")
    @SaCheckPermission(value = "admin-update",orRole = "admin")
    public SaResult updateBrand(@RequestBody Brand brand) {
        int i = brandService.updateBrand(brand);
        if(i==0){
            return SaResult.error("更新失败");
        }else {
            return SaResult.ok("更新成功");
        }
    }
    @RequestMapping("getCarver")
    @SaCheckPermission(orRole = {"admin","shop"})
    public SaResult selectCarver(){
        List<Carver> carvers = brandService.selectCarver();
        return SaResult.data(carvers);
    }
}
