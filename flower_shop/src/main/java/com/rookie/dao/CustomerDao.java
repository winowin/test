package com.rookie.dao;

import com.rookie.pojo.Customer;
import com.rookie.pojo.OldPassword;

import java.util.List;

public interface CustomerDao {
    Customer customerLogin(String phonenumber, String password);

    void customerRegister(Customer customer);

    Customer queryUserById(String uid);

    Customer getInfo(Integer uid);

    void updateInfo(Customer customer, Integer uid);

    void updatePwd(Integer uid, String newpwd);

    void compareOldpwd(OldPassword oldPassword);

    OldPassword obtainOldpwd(String oldpwd);

    List<Customer> allCustomerPhonenumber();
}
