package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.ProductMapper;
import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.pojo.ProductExample;
import com.charles.ssm.pojo.ProductImage;
import com.charles.ssm.service.CategoryService;
import com.charles.ssm.service.ProductImageService;
import com.charles.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private CategoryService categoryService;

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

    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }
    public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }

    @Override
    public List<Product> list() {
        ProductExample example = new ProductExample();
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);
        return products;
    }

    @Override
    public List<Product> listByCategoryId(int categoryId) {
        ProductExample example = new ProductExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andCidEqualTo(categoryId);
        List<Product> products = productMapper.selectByExample(example);
        setFirstProductImage(products);
        setCategory(products);
        return products;
    }

    @Override
    public Product get(int productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public void fill(List<Category> categories) {
        for (Category c : categories)
            fill(c);
    }

    @Override
    public void fill(Category c) {
        List<Product> products = listByCategoryId(c.getId());
        c.setProducts(products);
    }

    /**
     * 每行8个商品
     *
     * @param categories
     */
    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category c : categories) {
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEacchRow = products.subList(i, size);
                productsByRow.add(productsOfEacchRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.TYPE_SINGLE);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }


    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }
}
