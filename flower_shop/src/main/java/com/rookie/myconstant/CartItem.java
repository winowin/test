package com.rookie.myconstant;

import com.rookie.pojo.Flowers;

import java.io.Serializable;

public class CartItem  implements Serializable {
    private Flowers flowers;
    //小计数量
    private Integer num ;
    //小计金额=单价*数量
    private double smallPrice;
    public CartItem(Flowers flowers){
        this.num = 1;
        this.flowers = flowers;
    }
    //获取小计
    public double getSmallPrice(){
        this.smallPrice = 0f;
        return smallPrice=flowers.getPrice()*num;

    }
    public void setSmallPrice(double smallPrice){
        this.smallPrice = smallPrice;
    }


    public Flowers getFlowers() {
        return flowers;
    }
    public CartItem(Flowers flowers,Integer num){
        this.num = num;
        this.flowers = flowers;
    }
    public void setFlowers(Flowers flowers) {
        this.flowers = flowers;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
