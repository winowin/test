package com.rookie.dao;

import com.rookie.myconstant.CartItem;
import com.rookie.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void addOrderItems(Integer uid, CartItem value);

    Integer orderItemCount(Integer uid);

    List<OrderItem> allItemRecords(Integer uid, Integer recordsCount);

    void clearStatus();

    void addOrderId(Integer itemid, Integer orderid);

    List<OrderItem> allOrderItemInfo(Integer orderid);
}
