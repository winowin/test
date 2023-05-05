package com.rookie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayMethod implements Serializable {
    private Integer paymethodid;
    private String mathodname;
    private String note;
}
