package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem implements Serializable {
    private Integer itemid;//订单项表id
    private Integer flowerid;//花表id
    private Integer quantity;//数量
    private double flowerprice;//单价
    private double smallprice;//小计=单价*数量
    private Integer uid;//客户id
    private String flowername;//商品名
    private String image;//商品图
    private Integer status;
    private Integer orderid;//订单id
}
