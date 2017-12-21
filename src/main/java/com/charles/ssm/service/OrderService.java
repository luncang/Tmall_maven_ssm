package com.charles.ssm.service;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Order;
import com.charles.ssm.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> list(Page page);
    List<OrderItem> listByOrderId(int oid);
    void init(List<Order> orders);
}
