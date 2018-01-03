package com.charles.ssm.service;

import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Product;

import java.util.List;

public interface ProductService {
    int add(Product product);

    int delete(int productId);

    int update(Product product);

    List<Product> list();

    List<Product> listByCategoryId(int categoryId);

    Product get(int productId);

    void fill(List<Category> categories);

    void fill(Category c);

    void fillByRow(List<Category> categories);

    void setFirstProductImage(Product product);

}
