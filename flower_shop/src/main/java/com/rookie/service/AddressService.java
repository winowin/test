package com.rookie.service;

import com.rookie.pojo.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address, Integer uid);

    Address addressQueryByUid(Integer uid);


    Integer addressCount(Integer uid);

    List<Address> allAddress(Integer uid, Integer count);

    void updateAddress(Address address, Integer aid);

    void deleteAddress(Integer aid);

    Address addressQueryByAid(Integer aid);

    void setNonDefault(Integer uid);

    void setIsDefault(Integer aid);
}
