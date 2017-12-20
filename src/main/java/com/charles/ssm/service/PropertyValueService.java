package com.charles.ssm.service;

import com.charles.ssm.pojo.Product;
import com.charles.ssm.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {


    List<PropertyValue> listByProductId(int pid);

    int update(PropertyValue pv);

    void init(Product p);

    PropertyValue get(int ptid,int pid);
}
