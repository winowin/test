package com.rookie.myconstant;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
    //分页记录
    private List records;
    //当前页码
    private Integer pageNum = 1;
    //总页数
    private Integer totalPage;
    //上一页页码
    private Integer prePageNum;
    //下一页页码
    private Integer nextPageNum;
    //每页显示的记录条数
    private Integer pageSize = 9;
    //总记录条数
    private Integer totalRecords;
    //每页开始记录的索引
    private Integer startIndex;

    public PageBean(Integer pageNum, Integer totalRecords) {
        this.pageNum = pageNum;
        this.totalRecords = totalRecords;

        //计算总页数
        totalPage = totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
        //计算上一页页码
        prePageNum = Math.max(pageNum - 1, 1);
        //计算下一页页码
        nextPageNum = pageNum+1>totalPage?totalPage:pageNum+1;
        //开始记录的索引
        startIndex = (pageNum-1)*pageSize;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPrePageNum() {
        return prePageNum;
    }

    public void setPrePageNum(Integer prePageNum) {
        this.prePageNum = prePageNum;
    }

    public Integer getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(Integer nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
