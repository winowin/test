package com.rookie.service.impl;

import com.rookie.dao.CustomerDao;
import com.rookie.dao.impl.CustomerDaoImpl;
import com.rookie.pojo.Customer;
import com.rookie.pojo.OldPassword;
import com.rookie.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public Customer login(String phonenumber, String password) {
        return customerDao.customerLogin(phonenumber,password);
    }

    @Override
    public void regist(Customer customer) {
        customerDao.customerRegister(customer);
    }

    @Override
    public Customer queryUserById(String uid) {
        return customerDao.queryUserById(uid);
    }

    @Override
    public Customer getInfo(Integer uid) {
        return customerDao.getInfo(uid);
    }

    @Override
    public void updateInfo(Customer customer, Integer uid) {
        customerDao.updateInfo(customer,uid);
    }

    @Override
    public void updatePwd(Integer uid, String newpwd) {
        customerDao.updatePwd(uid,newpwd);
    }

    @Override
    public void compareOldpwd(OldPassword oldPassword) {
        customerDao.compareOldpwd(oldPassword);
    }

    @Override
    public OldPassword obtainOldpwd(String oldpwd) {

        return customerDao.obtainOldpwd(oldpwd);
    }

    @Override
    public List<Customer> allCustomerPhonenumber() {
        return customerDao.allCustomerPhonenumber();
    }
}
