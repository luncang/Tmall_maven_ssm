package com.charles.ssm.service;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Order;
import com.charles.ssm.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order c);

    void delete(int id);

    List<Order> list(Page page);

//    void init(List<Order> orders);

    void update(Order order);

    Order get(int id);

}
