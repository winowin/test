package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlowerKind implements Serializable {
    private Integer kindid;
    private String kindname;
    private String description;
}
