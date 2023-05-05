package com.rookie.service.impl;

import com.rookie.dao.OrderDao;
import com.rookie.dao.impl.OrderDaoImpl;
import com.rookie.pojo.Order;
import com.rookie.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    public OrderDao orderDao = new OrderDaoImpl();
    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }

    @Override
    public List<Order> allOrderInfo(Integer uid) {
        return orderDao.allOrderInfo(uid);
    }

    @Override
    public void addNewEmptyOrder(Order order) {
        orderDao.newEmptyOrder(order);
    }

    @Override
    public Order queryThisOrder(String ordernum) {

        return orderDao.queryThisOrder(ordernum);
    }

    @Override
    public void setOrderPaymethod(Order order) {
        orderDao.setOrderPaymethod(order);
    }

    @Override
    public Order queryAOrder(Integer orderid) {
        return orderDao.queryAOrder(orderid);
    }

    @Override
    public List<Order> allOrderTypeInfo(Integer uid, Integer status) {
        return orderDao.allOrderTypeInfo(uid,status);
    }
}
