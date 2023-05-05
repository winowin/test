package com.rookie.service;

import com.rookie.pojo.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);

    List<Order> allOrderInfo(Integer uid);

    void addNewEmptyOrder(Order order);

    Order queryThisOrder(String ordernum);

    void setOrderPaymethod(Order order);

    Order queryAOrder(Integer orderid);

    List<Order> allOrderTypeInfo(Integer uid, Integer status);
}
