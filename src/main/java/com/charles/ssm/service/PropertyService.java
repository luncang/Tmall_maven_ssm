package com.charles.ssm.service;

import com.charles.ssm.pojo.Property;

import java.util.List;

public interface PropertyService {

    List<Property> listByCategoryId(int categoryId);

    int add(Property property);

    void delete(int propertyId);

    int update(Property property);

    Property get(int propertyId);

}
