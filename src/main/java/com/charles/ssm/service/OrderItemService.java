package com.charles.ssm.service;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Order;
import com.charles.ssm.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem c);

    void delete(int id);

    void update(OrderItem c);

    OrderItem get(int id);

    List list();

    void fill(List<Order> os);

    void fill(Order o);

}
