package com.rookie.service;

import com.rookie.myconstant.PageBean;
import com.rookie.pojo.FlowerKind;
import com.rookie.pojo.Flowers;

import java.util.List;

public interface FlowerService {
    PageBean queryFlowersPageBean(String pageNum);

    Flowers queryFlowerById(Integer flowerid);

    PageBean queryFlowers(String pageNum, String flowerName);

    Flowers flowersMaxSales();

    void setSalesAndAmount(Flowers flowers1);


    List<FlowerKind> queryFlowerkind();

    PageBean queryFlowersKind(String pageNum, Integer kid);
}
