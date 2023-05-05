package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address implements Serializable {
    private Integer addressid;//地址表id
    private String province;//省
    private String city;//市
    private String county;//区县
    private String address;//详细地址
    private String username;//收件人姓名
    private String phonenumber;//手机号
    private Integer zipcode;//邮政编码
    private String nickname;//地址别称
    private Integer uid;//客户表id
    private Integer status;//状态

}
