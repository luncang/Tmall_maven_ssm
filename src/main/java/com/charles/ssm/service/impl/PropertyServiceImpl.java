package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.PropertyMapper;
import com.charles.ssm.pojo.Property;
import com.charles.ssm.pojo.PropertyExample;
import com.charles.ssm.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public List<Property> listByCategoryId(int categoryId) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andCidEqualTo(categoryId);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }

    @Override
    public int add(Property property) {
        return propertyMapper.insert(property);
    }

    @Override
    public void delete(int propertyId) {
        propertyMapper.deleteByPrimaryKey(propertyId);
    }

    @Override
    public int update(Property property) {
        return propertyMapper.updateByPrimaryKey(property);
    }

    @Override
    public Property get(int propertyId) {
        return propertyMapper.selectByPrimaryKey(propertyId);
    }
}
