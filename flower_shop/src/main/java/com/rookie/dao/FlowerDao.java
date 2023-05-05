package com.rookie.dao;

import com.rookie.pojo.FlowerKind;
import com.rookie.pojo.Flowers;

import java.util.List;

public interface FlowerDao {
    int queryFlowersTotalRecords();

    List<Flowers> findPageFlowers(Integer startIndex, Integer pageSize);

    Flowers queryFlowerById(Integer flowerid);

    List<Flowers> findPageFlowerName( String flowerName);

    List<Flowers> findPageFlowerName(Integer startIndex, Integer pageSize, String flowerName);

    Flowers flowersMaxSales();

    void setSalesAndAmount(Flowers flowers1);

    List<FlowerKind> queryFlowerkind();

    List<Flowers> findPageFlowerKind(Integer kid);

    List<Flowers> findPageFlowerKind(Integer startIndex, Integer pageSize, Integer kid);
}
