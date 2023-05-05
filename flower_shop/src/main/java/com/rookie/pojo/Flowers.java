package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flowers implements Serializable {
    private Integer flowerid;
    private String flowername;
    private Float price;
    private String image;
    private String description;
    private Boolean stockstatus = true;
    private Integer amount;
    private Integer kindid;
    private Integer sales;
    private Integer salesid;
}
