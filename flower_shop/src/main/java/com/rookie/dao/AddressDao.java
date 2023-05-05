package com.rookie.dao;

import com.rookie.pojo.Address;

import java.util.List;

public interface AddressDao {
    void addAdress(Address address, Integer uid);

    Address QueryByUid(Integer uid);

    Integer addressRecordsCount(Integer uid);

    List<Address> allAddressRecords(Integer uid, Integer count);

    void updateAddress(Address address, Integer aid);

    void deleteAddress(Integer aid);

    Address addressQueryByAid(Integer aid);

    void setNonDefault(Integer uid);

    void setIsDefault(Integer aid);
}
