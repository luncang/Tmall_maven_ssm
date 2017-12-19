package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.ProductImageMapper;
import com.charles.ssm.pojo.ProductImage;
import com.charles.ssm.pojo.ProductImageExample;
import com.charles.ssm.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;
    @Override
    public int insert(ProductImage pi) {

        return productImageMapper.insert(pi);
    }

    @Override
    public int delete(int id) {
        return productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public List<ProductImage> list(int pid,String type) {
       ProductImageExample example = new ProductImageExample();
       example.setOrderByClause("id desc");
       example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
        return  productImageMapper.selectByExample(example);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }
}
