package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.OrderMapper;
import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Order;
import com.charles.ssm.pojo.OrderExample;
import com.charles.ssm.pojo.OrderItem;
import com.charles.ssm.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> list(Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> os = orderMapper.selectByExample(example);
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);
        return os;
    }

    @Override
    public List<OrderItem> listByOrderId(int oid) {
        return null;
    }

    @Override
    public void init(List<Order> orders) {

    }
}
