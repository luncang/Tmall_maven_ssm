package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.ProductMapper;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.pojo.ProductExample;
import com.charles.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int delete(int productId) {
        return productMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public int update(Product product) {
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public List<Product> list() {
        ProductExample example = new ProductExample();
        example.setOrderByClause("id desc");
        return productMapper.selectByExample(example);
    }

    @Override
    public List<Product> listByCategoryId(int categoryId) {
        ProductExample example = new ProductExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andCidEqualTo(categoryId);
        return productMapper.selectByExample(example);
    }

    @Override
    public Product get(int productId) {
        return productMapper.selectByPrimaryKey(productId);
    }
}
