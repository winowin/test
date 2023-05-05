package com.rookie.dao.impl;

import com.rookie.dao.OrderItemDao;
import com.rookie.myconstant.CartItem;
import com.rookie.pojo.OrderItem;
import com.rookie.utils.HikariCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    public QueryRunner qr = new QueryRunner(HikariCPUtil.getDataSource());
    @Override
    public void addOrderItems(Integer uid, CartItem value) {
        String sql = "INSERT INTO `orderitem` VALUES (NULL,?,?,?,?,?,?,?,null,1,null)";
        try {
            qr.update(sql,value.getFlowers().getFlowerid(),value.getNum(),value.getFlowers().getPrice(),
                    value.getSmallPrice(),value.getFlowers().getDescription(),uid,value.getFlowers().getFlowername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer orderItemCount(Integer uid) {
        String sql = "SELECT COUNT(uid=? and `status`=1) FROM `orderitem`";
        try {
            Long count = qr.query(sql,new ScalarHandler<>(),uid);
            return count.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<OrderItem> allItemRecords(Integer uid, Integer recordsCount) {
        String sql = "SELECT * FROM `orderitem` where uid=? and `status`=1  LIMIT 0,?";
        try {
            return qr.query(sql,new BeanListHandler<>(OrderItem.class),uid,recordsCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void clearStatus() {
        String sql = "UPDATE `orderitem` SET `status`= 0";
        try {
            qr.update(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrderId(Integer itemid, Integer orderid) {

        String sql = "UPDATE `orderitem` SET `orderid` = ? WHERE itemid = ?";

        try {
            qr.update(sql,orderid,itemid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderItem> allOrderItemInfo(Integer orderid) {
        String sql = "SELECT * FROM `orderitem` where orderid=?";
        try {
            return qr.query(sql,new BeanListHandler<>(OrderItem.class),orderid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
