package com.rookie.service.impl;

import com.rookie.dao.FlowerDao;
import com.rookie.dao.impl.FlowerDaoImpl;
import com.rookie.myconstant.PageBean;
import com.rookie.pojo.FlowerKind;
import com.rookie.pojo.Flowers;
import com.rookie.service.FlowerService;
import com.rookie.utils.StringUtils;

import java.util.List;

public class FlowerSrviceImpl implements FlowerService {
    public FlowerDao flowerDao = new FlowerDaoImpl();
    @Override
    public PageBean queryFlowersPageBean(String pageNum) {
        int num = 1;//默认查看的页码
        if(StringUtils.isNotEmpty(pageNum))
            num = Integer.parseInt(pageNum);
        int totalRecords = flowerDao.queryFlowersTotalRecords();
        PageBean pageBean = new PageBean(num,totalRecords);
        List<Flowers> flowers = flowerDao.findPageFlowers(pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setRecords(flowers);
        return pageBean;
    }

    @Override
    public Flowers queryFlowerById(Integer flowerid) {
        return flowerDao.queryFlowerById(flowerid);
    }

    @Override
    public PageBean queryFlowers(String pageNum, String flowerName) {
        int num = 1;//默认查看的页码
        if(StringUtils.isNotEmpty(pageNum))
            num = Integer.parseInt(pageNum);
        List<Flowers> flowers = flowerDao.findPageFlowerName(flowerName);
        int totalRecords = flowers.size();
        PageBean pageBean = new PageBean(num,totalRecords);
        List<Flowers> flowers1 = flowerDao.findPageFlowerName(pageBean.getStartIndex(),pageBean.getPageSize(),flowerName);
        pageBean.setRecords(flowers1);
        return pageBean;
    }

    @Override
    public Flowers flowersMaxSales() {
        return flowerDao.flowersMaxSales();
    }

    @Override
    public void setSalesAndAmount(Flowers flowers1) {
        flowerDao.setSalesAndAmount(flowers1);
    }

    @Override
    public List<FlowerKind> queryFlowerkind() {
        return flowerDao.queryFlowerkind();
    }

    @Override
    public PageBean queryFlowersKind(String pageNum, Integer kid) {
        int num = 1;//默认查看的页码
        if(StringUtils.isNotEmpty(pageNum))
            num = Integer.parseInt(pageNum);
        List<Flowers> flowers = flowerDao.findPageFlowerKind(kid);
        int totalRecords = flowers.size();
        PageBean pageBean = new PageBean(num,totalRecords);
        List<Flowers> flowers1 = flowerDao.findPageFlowerKind(pageBean.getStartIndex(),pageBean.getPageSize(),kid);
        pageBean.setRecords(flowers1);
        return pageBean;
    }
}
