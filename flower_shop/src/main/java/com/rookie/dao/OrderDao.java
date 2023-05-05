package com.rookie.dao;

import com.rookie.pojo.Order;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order);

    List<Order> allOrderInfo(Integer uid);

    void newEmptyOrder(Order order);

    Order queryThisOrder(String ordernum);

    void setOrderPaymethod(Order order);

    Order queryAOrder(Integer orderid);

    List<Order> allOrderTypeInfo(Integer uid, Integer status);
}
