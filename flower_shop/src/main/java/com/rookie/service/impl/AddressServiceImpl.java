package com.rookie.service.impl;

import com.rookie.dao.AddressDao;
import com.rookie.dao.impl.AddressDaoImpl;
import com.rookie.pojo.Address;
import com.rookie.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    public AddressDao addressDao = new AddressDaoImpl();
    @Override
    public void addAddress(Address address, Integer uid) {
        addressDao.addAdress(address,uid);
    }

    @Override
    public Address addressQueryByUid(Integer uid) {
        return addressDao.QueryByUid(uid);
    }

    @Override
    public Integer addressCount(Integer uid) {
        return addressDao.addressRecordsCount(uid);
    }

    @Override
    public List<Address> allAddress(Integer uid, Integer count) {
        return addressDao.allAddressRecords(uid,count);
    }

    @Override
    public void updateAddress(Address address, Integer aid) {
        addressDao.updateAddress(address,aid);
    }

    @Override
    public void deleteAddress(Integer aid) {
        addressDao.deleteAddress(aid);
    }

    @Override
    public Address addressQueryByAid(Integer aid) {
        return addressDao.addressQueryByAid(aid);
    }

    @Override
    public void setNonDefault(Integer uid) {
        addressDao.setNonDefault( uid);
    }

    @Override
    public void setIsDefault(Integer aid) {
        addressDao.setIsDefault(aid);
    }
}
