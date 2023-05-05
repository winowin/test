package com.rookie.myconstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo implements Serializable {
    private int code;//代码
    private boolean success;//是否成功
    private String message;//消息
    private Object data;//数据

    public ResultInfo(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
