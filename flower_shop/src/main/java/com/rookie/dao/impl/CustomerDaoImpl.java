package com.rookie.dao.impl;

import com.rookie.dao.CustomerDao;
import com.rookie.pojo.Customer;
import com.rookie.pojo.OldPassword;
import com.rookie.utils.HikariCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private QueryRunner qr = new QueryRunner(HikariCPUtil.getDataSource());


    @Override
    public Customer customerLogin(String phonenumber, String password) {
        String sql  = "SELECT * FROM customer WHERE phonenumber=? AND `password`=PASSWORD(?)";

        try {
            return qr.query(sql,new BeanHandler<>(Customer.class),phonenumber,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void customerRegister(Customer customer) {
        String sql  = "insert into customer values(null,?,PASSWORD(?),?,null,null,null)";
        try {
            qr.update(sql,customer.getUsername(),customer.getPassword(),customer.getPhonenumber());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer queryUserById(String uid) {
        String sql  = "SELECT * FROM customer WHERE uid=?";
        try {
            return qr.query(sql,new BeanHandler<>(Customer.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getInfo(Integer uid) {
        String sql  = "SELECT * FROM `customer` WHERE uid=?";
        try {
            return qr.query(sql,new BeanHandler<>(Customer.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateInfo(Customer customer, Integer uid) {
        String sql = "UPDATE `customer` SET `username`=?,`phonenumber`=?,`usergender`=?,`birthday`=?,`avatar`=? WHERE `uid` = ?";
        try {
            qr.update(sql,customer.getUsername(),customer.getPhonenumber(),
                    customer.getUsergender(),customer.getBirthday(),customer.getAvatar(),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePwd( Integer uid, String newpwd) {
        String sql = "UPDATE `customer` SET `password`=PASSWORD(?) WHERE `uid` = ? ";
        try {
            qr.update(sql,newpwd,uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void compareOldpwd(OldPassword oldPassword) {
        String sql = "insert into `old_pwd` values(null,PASSWORD(?))";
        try {
            qr.update(sql,oldPassword.getOldpassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OldPassword obtainOldpwd(String oldpwd) {
        String sql = "SELECT *FROM `old_pwd` WHERE oldpassword=PASSWORD(?)";
        try {
            return qr.query(sql,new BeanHandler<>(OldPassword.class),oldpwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> allCustomerPhonenumber() {
        String sql = "SELECT * FROM `customer`";
        try {
            return qr.query(sql,new BeanListHandler<>(Customer.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
