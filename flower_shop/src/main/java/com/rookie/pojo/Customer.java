package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer implements Serializable {
    private Integer uid;//用户id
    private String username;//用户名昵称
    private String password;//用户密码
    private String phonenumber;//手机号
    private String usergender;//用户性别
    private String birthday;//生日
    private String avatar;//头像
}
