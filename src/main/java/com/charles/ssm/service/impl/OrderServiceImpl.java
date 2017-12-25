package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.OrderItemMapper;
import com.charles.ssm.mapper.OrderMapper;
import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.*;
import com.charles.ssm.service.OrderService;
import com.charles.ssm.service.ProductImageService;
import com.charles.ssm.service.ProductService;
import com.charles.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;


    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Order> list(Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> os = orderMapper.selectByExample(example);
        setUser(os);
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);
        return os;
    }

    private void setUser(List<Order> os) {
        for (Order o : os)
            setUser(o);
    }

    public void setUser(Order o) {
        if(o==null)
            return;
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }

//    @Override
//    public List<OrderItem> listByOrderId(int oid) {
//        OrderItemExample example = new OrderItemExample();
//        example.setOrderByClause("id desc");
//        example.createCriteria().andOidEqualTo(oid);
//        return orderItemMapper.selectByExample(example);
//    }

//    @Override
//    public void init(List<Order> orders) {
//        for (Order order : orders) {
//
//            List<OrderItem> orderItems = listByOrderId(order.getId());
//            order.setOrderItems(orderItems);
//            int totalNumber =0,total=0;
//
//
//            for (OrderItem orderItem : orderItems) {
//                Product product = productService.get(orderItem.getPid());
//                orderItem.setProduct(product);
//                List<ProductImage> single = productImageService.list(product.getId(), ProductImageService.TYPE_SINGLE);
//                product.setProductSingleImages(single);
//                product.setFirstProductImage(single.get(0));
//                List<ProductImage> details = productImageService.list(product.getId(), ProductImageService.TYPE_DETAIL);
//                product.setProductDetailImages(details);
//                totalNumber +=orderItem.getNumber();
//                total +=orderItem.getNumber()*product.getPromotePrice();
//            }
//
//            User user = userService.get(order.getUid());
//
//            order.setUser(user);
//            order.setTotal(total);
//            order.setTotalNumber(totalNumber);
//
//        }
//    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

}
