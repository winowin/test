package com.rookie.dao.impl;

import com.rookie.dao.OrderDao;
import com.rookie.pojo.Order;
import com.rookie.utils.HikariCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    public QueryRunner qr = new QueryRunner(HikariCPUtil.getDataSource());
    @Override
    public void saveOrder(Order order) {
//        String sql = "INSERT INTO `order` VALUES (NULL,?,?,?,?,?,?,?,?,null,?,?)";
        String sql= "UPDATE `order` SET `uid`= ?, `username`= ?, `address`= ?, `phonenumber`= ?, `totalnum`= ?, `totalprice`= ?,`status`= ?, `createdate`= ?,`datetime`= ? where `orderid`=?";
        try {
            qr.update(sql,order.getUid(),order.getUsername(),order.getAddress(),
                    order.getPhonenumber(),order.getTotalnum(),order.getTotalprice(),
                    order.getStatus(),order.getCreatedate(),order.getDatetime(),order.getOrderid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> allOrderInfo(Integer uid) {
        String sql = "select * from `order` where uid=?";

        try {
            return qr.query(sql,new BeanListHandler<>(Order.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newEmptyOrder(Order order) {
        String sql = "INSERT INTO `order`(orderid,ordernum) VALUES (NULL,?)";
        try {
            qr.update(sql,order.getOrdernum());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order queryThisOrder(String ordernum) {
        String sql = "select * from `order` where ordernum=?";
        try {
            return qr.query(sql,new BeanHandler<>(Order.class),ordernum);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setOrderPaymethod(Order order) {
        String sql = "UPDATE `order` SET `paymethodid`=? ,`status`= 1 where orderid=?";
        try {
            qr.update(sql,order.getPaymethodid(),order.getOrderid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order queryAOrder(Integer orderid) {
        String sql = "select * from `order` where orderid=?";
        try {
            return qr.query(sql,new BeanHandler<>(Order.class),orderid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> allOrderTypeInfo(Integer uid, Integer status) {
        String sql = "select * from `order` where uid=? and status=?";

        try {
            return qr.query(sql,new BeanListHandler<>(Order.class),uid,status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
