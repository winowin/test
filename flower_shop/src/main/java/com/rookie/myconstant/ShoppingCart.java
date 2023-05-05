package com.rookie.myconstant;


import com.rookie.pojo.Flowers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//创建购物车存放cart_item
public class ShoppingCart implements Serializable {
    //存放数据：key，购物项对应商品的id（因为一项对应一件商品）。value就是购物项
    private Map<Integer,CartItem> map = new HashMap<>();
    private Integer totalNum;//商品总件数
    private double totalPrice;//总金额（支付金额）

    //删除cart_item
    public void removeFlowers(Integer flowerid){
        map.remove(flowerid);
    }

    //添加cartItem
    public void addFlowersToCart(Flowers flowers , Integer num){
        //如果存在则取出并累计数量
        if (map.containsKey(flowers.getFlowerid())){

            CartItem item = map.get(flowers.getFlowerid());
            item.setNum(item.getNum()+num);
        }
        //如果不存在则新加入其中
        else {
            CartItem item = new CartItem(flowers,num);
            map.put(flowers.getFlowerid(),item);
        }
    }


    //计算商品总数
    public Integer getTotalNum(){
        this.totalNum = 0;
        for (Map.Entry<Integer,CartItem> me:map.entrySet()){
            totalNum+=me.getValue().getNum();
        }
        return totalNum;
    }

    //计算总价格
    public double getTotalPrice(){
        this.totalPrice = 0f;
        for(Map.Entry<Integer,CartItem> me:map.entrySet()){
            totalPrice+=me.getValue().getSmallPrice();
        }
        return totalPrice;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, CartItem> getMap() {
        return map;
    }
}
