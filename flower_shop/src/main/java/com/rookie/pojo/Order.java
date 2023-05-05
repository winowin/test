package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order  implements Serializable {
    private Integer orderid;//订单id
    private String ordernum;//订单id
    private Integer uid;//客户id
    private String username;//客户名
    private String address;//详细地址
    private String phonenumber;//手机号
    private double totalprice;///总金额
    private Integer totalnum;//总数
    private Integer status;//订单状态
    private Integer paymethodid;//支付表id
    private Timestamp createdate;//下单时间
    private String datetime;//送达时间
    private List<OrderItem> items;
    private String createtime;//下单时间
}
