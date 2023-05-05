package com.rookie.service.impl;

import com.rookie.dao.OrderItemDao;
import com.rookie.dao.impl.OrderItemDaoImpl;
import com.rookie.myconstant.CartItem;
import com.rookie.pojo.OrderItem;
import com.rookie.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    public OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Override
    public void addOrderItem(Integer uid, CartItem value) {
        orderItemDao.addOrderItems(uid,value);
    }

    @Override
    public Integer orderItemCount(Integer uid) {
        return orderItemDao.orderItemCount(uid);
    }

    @Override
    public List<OrderItem> allItemRecords(Integer uid, Integer recordsCount) {
        return orderItemDao.allItemRecords(uid,recordsCount);
    }

    @Override
    public void clearStatus() {
        orderItemDao.clearStatus();
    }

    @Override
    public void addOrderId(Integer itemid, Integer orderid) {
        orderItemDao.addOrderId(itemid,orderid);
    }

    @Override
    public List<OrderItem> allOrderItemInfo(Integer orderid) {
        return orderItemDao.allOrderItemInfo(orderid);
    }
}
