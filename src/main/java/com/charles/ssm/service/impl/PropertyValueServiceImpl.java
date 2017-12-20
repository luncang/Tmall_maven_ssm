package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.PropertyValueMapper;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.pojo.Property;
import com.charles.ssm.pojo.PropertyValue;
import com.charles.ssm.pojo.PropertyValueExample;
import com.charles.ssm.service.PropertyService;
import com.charles.ssm.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;

    @Autowired
    PropertyService propertyService;

    @Override
    public List<PropertyValue> listByProductId(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andPidEqualTo(pid);

        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : pvs) {
            pv.setProperty(propertyService.get(pv.getPid()));
        }

        return pvs;
    }

    @Override
    public int update(PropertyValue pv) {

        return propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    public void init(Product p) {
        List<Property> pts = propertyService.listByCategoryId(p.getCid());
        for (Property pt : pts) {
            PropertyValue pv = get(pt.getId(),p.getId());
            if(null==pv){
                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid).andPtidEqualTo(ptid);

        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty())
            return null;
        return pvs.get(0);
    }
}
