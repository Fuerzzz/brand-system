package com.chinahitech.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.Brand;
import com.chinahitech.shop.mapper.BrandMapper;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.image.BandCombineOp;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;
    public Page<Brand> findAll(Page<Brand> page) {
        return brandMapper.selectPage(page,null);
    }

    public int add(Brand brand, Long categoryId) {
        int brandResult = brandMapper.insert(brand);

        int categoryResult = brandMapper.insertBrandAndCategory(categoryId,brand.getId());
        if(brandResult + categoryResult > 1){
            return 1;
        }
        return 0;
    }

    public Brand findById(Long id) {
        return brandMapper.selectById(id);
    }

    public int delete(Long id) {
        return  brandMapper.deleteById(id);
    }

    public int edit(Brand brand) {
        return brandMapper.updateById(brand);
    }
}
