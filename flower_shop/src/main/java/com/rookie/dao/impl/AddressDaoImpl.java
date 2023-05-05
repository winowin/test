package com.rookie.dao.impl;

import com.rookie.dao.AddressDao;
import com.rookie.pojo.Address;
import com.rookie.utils.HikariCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    public QueryRunner qr = new QueryRunner(HikariCPUtil.getDataSource());
    @Override
    public void addAdress(Address address, Integer uid) {
        String sql = "INSERT INTO `t_address` VALUES (NULL,?,?,?,?,?,?,?,?,?,0)";
        try {
            qr.update(sql,address.getProvince(),address.getCity(),
                    address.getCounty(),address.getAddress(),address.getUsername(),
                    address.getPhonenumber(),address.getZipcode(),address.getNickname(),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address QueryByUid(Integer uid) {
        String sql = "SELECT * FROM `t_address` WHERE `uid` = ? and `status`=1";
        try {
            return qr.query(sql,new BeanHandler<>(Address.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer addressRecordsCount(Integer uid) {
        String sql = "SELECT COUNT(uid=?) FROM `t_address`";
        try {
           Long count = qr.query(sql,new ScalarHandler<>(),uid);
           return count.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Address> allAddressRecords(Integer uid, Integer count) {
        String sql = "SELECT * FROM `t_address` where uid=? LIMIT 0,?";

        try {
            return qr.query(sql,new BeanListHandler<>(Address.class),uid,count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAddress(Address address, Integer aid) {
        String sql = "update `t_address` set `province`=?,`city`=?,`county`=?,`address`=?,`username`=?,`phonenumber`=?,`zipcode`=?,`nickname`=?  where `addressid`=?";

        try {
            qr.update(sql,address.getProvince(),address.getCity(),address.getCounty(),address.getAddress(),address.getUsername(),
                    address.getPhonenumber(),address.getZipcode(),address.getNickname(),aid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAddress(Integer aid) {
        String sql = "DELETE  FROM `t_address`  WHERE addressid = ?";

        try {
            qr.update(sql,aid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address addressQueryByAid(Integer aid) {
        String sql = "SELECT * FROM `t_address` where addressid=?";

        try {
            return qr.query(sql, new BeanHandler<>(Address.class), aid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setNonDefault(Integer uid) {
        String sql = "update `t_address` set `status`=0 where uid=?";

        try {
            qr.update(sql,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setIsDefault(Integer aid) {
        String sql = "update `t_address` set `status`=1 where addressid=?";
        try {
            qr.update(sql,aid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
