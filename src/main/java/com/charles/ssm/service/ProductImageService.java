package com.charles.ssm.service;

import com.charles.ssm.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

    String TYPE_SINGLE="type_single";
    String TYPE_DETAIL="type_detail";

    int insert(ProductImage pi);

    int delete(int id);

    int update();

    List<ProductImage> list(int pid,String type);

    ProductImage get(int id);

    int deleteByProductId(int pid);
}
